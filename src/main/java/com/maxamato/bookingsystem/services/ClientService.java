package com.maxamato.bookingsystem.services;


import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
@Getter
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelRepository hotelRepository;

    public Client addClient(ClientRequest clientRequest) {
        String email = clientRequest.getEmail();
        if (clientRepository.existsByEmail(email)) {
            throw new IllegalStateException(new Exception("User with provided email address already exists."));
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
                clientRequest.getDateOfBirth()
        );
        clientRepository.save(client);
        return client;
    }

    public List<HotelRoom> addClientToHotelRoom(Long clientId, Long roomId) {
        if (!hotelRoomRepository.existsById(roomId)) {
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).get();

        if (!clientRepository.existsById(clientId)) {
            throw new IllegalStateException(new Exception("Client with provided id does not exist"));
        }
        Client client = clientRepository.findById(clientId).get();

        Hotel hotel = hotelRepository.findById(hotelRoom.getHotel().getHotelId()).orElseThrow(() -> new IllegalStateException(new Exception("Hotel does not exist")));

        System.out.println(hotel.getNumberOfRooms());

        client.getBookedRooms().add(hotelRoom);

        return hotelRoomRepository.findAll();

    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public String deleteClient(String clientEmail) {
        if (!clientRepository.existsByEmail(clientEmail)) {
            throw new IllegalStateException(new Exception("Client does not exist."));
        }
        Long clientId = clientRepository.findByEmail(clientEmail).getClientId();
        clientRepository.deleteById(clientId);
        return String.format("Client with id %s got deleted", clientId);
    }

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
}
