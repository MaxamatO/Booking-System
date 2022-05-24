package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Setter
@Getter
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelRepository hotelRepository;

    public List<ClientDto> findAllClients() {
        return clientRepository.findAll().stream().map(
                client -> new ClientDto(
                        client.getEmail(),
                        client.getDateOfBirth(),
                        client.getBookedRooms().stream().map(
                                hotelRoom -> new HotelRoomDto(
                                        hotelRoom.getIsAvailable(),
                                        hotelRoom.getNumberOfBeds(),
                                        hotelRoom.getHasPrivateToilet(),
                                        hotelRoom.getNumberOfClients()
                                )
                        ).collect(Collectors.toList()),
                        client.isAdult()
                )
        ).collect(Collectors.toList());
    }

    public ClientDto addClient(ClientRequest clientRequest) {
        String email = clientRequest.getEmail();
        if (clientRepository.existsByEmail(email)) {
            throw new IllegalStateException(new Exception("User with provided email address already exists."));
        }

        String whichAreBlank = blankFields(clientRequest);

        if(!whichAreBlank.equals("Everything covered.")){
            throw new IllegalStateException(new Exception(whichAreBlank));
        }

        String password = clientRequest.getPassword();

        if(!isValidEmailAddress(email)){
            throw new IllegalStateException(new Exception("Email not valid."));
        }

        if(!isValidPassword(password)){
            throw new IllegalStateException(new Exception("Password not valid"));
        }

        if(!isValidDateOfBirth(clientRequest.getDateOfBirth())){
            throw new IllegalStateException(new Exception("Date of birth not valid"));
        }

        String encodedPassword = encode(password);

        Client client = new Client(
                clientRequest.getEmail(),
                encodedPassword,
                clientRequest.getDateOfBirth(),
                clientRequest.getPostCode(),
                clientRequest.getStreet(),
                clientRequest.getCountry(),
                clientRequest.getCity(),
                clientRequest.getHouseNumber()
        );
        clientRepository.save(client);
        return new ClientDto(
                client.getEmail(),
                client.getDateOfBirth(),
                client.isAdult(),
                client.getCountry(),
                client.getCity(),
                client.getStreet(),
                client.getPostCode(),
                client.getHouseNumber()
        );
    }


    public ClientDto addClientToHotelRoom(Long clientId, Long roomId) {
        if (!hotelRoomRepository.existsById(roomId)) {
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).orElseThrow(() -> new IllegalStateException(new Exception("Hotel room does not exist")));
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalStateException(new Exception("Client with provided id does not exist"));
        }
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException(new Exception("Client does not exist")));
        client.getBookedRooms().add(hotelRoom);

        hotelRoom.getClients().add(client);

        return clientRepository.findById(clientId).map(client1 -> new ClientDto(
                client1.getEmail(),
                client1.getDateOfBirth(),
                client1.getBookedRooms().stream().map(
                        hR -> new HotelRoomDto(
                                hR.getIsAvailable(),
                                hR.getNumberOfBeds(),
                                hR.getHasPrivateToilet(),
                                hR.getNumberOfClients()
                        )
                ).collect(Collectors.toList()),
                client1.isAdult()
        )).orElseThrow(() -> new IllegalStateException(new Exception("Client does not exist")));

    }

    public List<ClientDto> findAllClientsRooms() {
        return clientRepository.findAll().stream().map(
                client -> new ClientDto(
                        client.getEmail(),
                        client.getDateOfBirth(),
                        client.getBookedRooms().stream().map(
                                hotelRoom -> new HotelRoomDto(
                                        hotelRoom.getIsAvailable(),
                                        hotelRoom.getNumberOfBeds(),
                                        hotelRoom.getHasPrivateToilet(),
                                        hotelRoom.getNumberOfClients()
                                )
                        ).collect(Collectors.toList()),
                        client.isAdult()
                )
                ).collect(Collectors.toList());
    }

    public List<ClientDto> findAllClientsAddress() {
        return clientRepository.findAll().stream().map(
                client -> new ClientDto(
                        client.getEmail(),
                        client.getDateOfBirth(),
                        client.isAdult(),
                        client.getCountry(),
                        client.getCity(),
                        client.getStreet(),
                        client.getPostCode(),
                        client.getHouseNumber()
                )
        ).collect(Collectors.toList());
    }

    // Not implemented yet
    // Foreign key error
    public String deleteClient(String clientEmail) {
        if (!clientRepository.existsByEmail(clientEmail)) {
            throw new IllegalStateException(new Exception("Client does not exist."));
        }
        Long clientId = clientRepository.findByEmail(clientEmail).getClientId();
        clientRepository.deleteById(clientId);
        return String.format("Client with id %s got deleted", clientId);
    }

    // PRIVATE FUNCTIONS USED FOR CHECKING CREDENTIALS
    private String encode(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode(password));
        return bCryptPasswordEncoder.encode(password);
    }
    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        }
        catch (AddressException ex){
            result = false;
        }
        return result;
    }
    private boolean isValidPassword(String password){
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialSymbols = "(.*[@$%^&*()_#!].*)";
        String numbers = "(.*[0-9].*)";

        if(password.length() < 8 || password.length() > 20){
            throw new IllegalStateException(new Exception("Password must contain 8-20 characters."));

        }

        if(!password.matches(upperCaseChars)){
            throw new IllegalStateException(new Exception("Password must contain at least one upper case character."));
        }

        if(!password.matches(lowerCaseChars)){
            throw new IllegalStateException(new Exception("Password can't contain only upper case characters."));
        }

        if(!password.matches(specialSymbols)){
            throw new IllegalStateException(new Exception("Password must contain at least one special character."));
        }

        if(!password.matches(numbers)){
            throw new IllegalStateException(new Exception("Password must contain at least one number"));
        }
        return true;
    }
    private boolean isValidDateOfBirth(LocalDate localDate){
        LocalDateTime todaysDate = LocalDateTime.now();
        return localDate.isBefore(ChronoLocalDate.from(todaysDate));
    }
    private String blankFields(ClientRequest clientRequest) {
        String email = clientRequest.getEmail();
        String password = clientRequest.getPassword();
        LocalDate dateOfBirth = clientRequest.getDateOfBirth();
        String city = clientRequest.getCity();
        String country = clientRequest.getCountry();
        int houseNumber = clientRequest.getHouseNumber();
        String postCode = clientRequest.getPostCode();
        String street = clientRequest.getStreet();

        if(email == null){
            return "Email field can't be empty.";
        }
        if(password == null){
            return "Password field can't be empty.";
        }
        if(dateOfBirth == null){
            return "Date of birth field can't be empty.";
        }
        if(city == null){
            return "City field can't be empty.";
        }
        if(country == null){
            return "Country field can't be empty.";
        }
        if(houseNumber == 0){
            return "House number field can't be empty.";
        }
        if(postCode == null){
            return "Postcode field can't be empty.";
        }
        if(street == null){
            return "Street field can't be empty.";
        }
        return "Everything covered.";
    }



}
