package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.BookingDto;
import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.BookingRequest;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.BookingRepository;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import com.maxamato.bookingsystem.utils.PasswordValidator;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
    private final BookingRepository bookingRepository;

    public List<ClientDto> findAllClients() {
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

    public ClientDto addClient(ClientRequest clientRequest) {
        String email = clientRequest.getEmail();
        if (clientRepository.existsByEmail(email)) {
            throw new IllegalStateException(new Exception("User with provided email address already exists."));
        }

        String whichAreBlank = blankFields(clientRequest);

        if (!whichAreBlank.equals("Everything covered.")) {
            throw new IllegalStateException(new Exception(whichAreBlank));
        }

        String password = clientRequest.getPassword();

        if (!isValidEmailAddress(email)) {
            throw new IllegalStateException(new Exception("Email not valid."));
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.test(password);

        if (!isValidDateOfBirth(clientRequest.getDateOfBirth())) {
            throw new IllegalStateException(new Exception("Date of birth not valid"));
        }

        if(!isSixteenOrOlder(clientRequest.getDateOfBirth())){
            throw new IllegalStateException(new Exception("You must be at least 16 years old to make an account"));
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




    public BookingDto addClientToHotelRoom(BookingRequest bookingRequest) {
        Long clientId = bookingRequest.getClientId();
        Long roomId = bookingRequest.getRoomId();
        long count = bookingRepository.getCountOfClients(clientId, roomId);
        if(count >= 1){
            throw new IllegalStateException(new Exception("This room is already booked for this user."));
        }
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException(new Exception("User with provided id does not exist")));
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).orElseThrow(() -> new IllegalStateException(new Exception("Room with provided id does not exist")));
        Booking booking = new Booking(
                client,
                hotelRoom,
                bookingRequest.getAccommodationDate(),
                bookingRequest.getEvictionDate()
                );
        bookingRepository.save(booking);
        hotelRoomRepository.executeNumberOfClientsUpdate();
        ClientDto clientDto = new ClientDto(
                client.getEmail(),
                client.getDateOfBirth()
        );
        HotelRoomDto hotelRoomDto = new HotelRoomDto(
                hotelRoom.getId(),
                booking.getTotalAmountOfNights()
        );

        return new BookingDto(
                clientDto,
                hotelRoomDto
        );
    }


    // TODO: Test it
    public ClientDto getBookingsForAClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()->
                new IllegalStateException(new Exception("User with provided id does not exist.")));
        List<Booking> bookings = bookingRepository.findAllBookingsByClientId(clientId);
        List<HotelRoom> hotelRooms = bookings.stream().map(Booking::getHotelRoom).collect(Collectors.toList());
        List<Integer> nights = bookings.stream().map(Booking::getTotalAmountOfNights).collect(Collectors.toList());

        return new ClientDto(
                client.getEmail(),
                client.getDateOfBirth(),
                hotelRooms.stream().map(
                        hotelRoom -> new HotelRoomDto(
                                hotelRoom.getId(),
                                nights.remove(0)
                        )
                ).collect(Collectors.toList())
                );
    }

    // TODO: Test it
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

    public ClientDto findClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException(
                        new Exception("User with provided email does not exist.")
                )
        );
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

    public String deleteClient(String clientEmail) {
        if(!clientRepository.existsByEmail(clientEmail)){
            throw new IllegalStateException(new Exception(
                    "Client provided email can't be deleted - doesn't exist."
            ));
        }
        Long id = clientRepository.findByEmail(clientEmail).orElseThrow(
                () -> new IllegalStateException(new Exception(
                "Client provided email can't be deleted - doesn't exist."
        ))).getId();
        bookingRepository.deleteByClientId(id);
        clientRepository.deleteById(id);
        return String.format("Client with email address: %s got deleted", clientEmail);
    }

    // PRIVATE FUNCTIONS USED FOR CHECKING CREDENTIALS
    private String encode(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private boolean isSixteenOrOlder(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 16;
    }

    private boolean isValidDateOfBirth(LocalDate localDate) {
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

        if (email == null) {
            return "Email field can't be empty.";
        }
        if (password == null) {
            return "Password field can't be empty.";
        }
        if (dateOfBirth == null) {
            return "Date of birth field can't be empty.";
        }
        if (city == null) {
            return "City field can't be empty.";
        }
        if (country == null) {
            return "Country field can't be empty.";
        }
        if (houseNumber == 0) {
            return "House number field can't be empty.";
        }
        if (postCode == null) {
            return "Postcode field can't be empty.";
        }
        if (street == null) {
            return "Street field can't be empty.";
        }
        return "Everything covered.";
    }

}
