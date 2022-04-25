package com.maxamato.bookingsystem.services;


import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Client addClient(ClientRequest clientRequest) {
        String email = clientRequest.getEmail();
        if(clientRepository.existsByEmail(email)){
            throw new IllegalStateException(new Exception("User with provided email address already exists."));
        }
        Client client = new Client(
                clientRequest.getEmail(),
                clientRequest.getPassword(),
                clientRequest.getDateOfBirth()
        );
        clientRepository.save(client);
        return client;
    }

    public List<HotelRoom> addClientToHotelRoom(Long clientId, Long roomId) {
        if(!hotelRoomRepository.existsById(roomId)){
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        HotelRoom hotelRoom = hotelRoomRepository.getById(roomId);

        if(!clientRepository.existsById(clientId)){
            throw new IllegalStateException(new Exception("Client with provided id does not exist"));
        }
        Client client = clientRepository.getById(clientId);

        clientRepository.save(
                new Client(
                        client.getEmail(),
                        client.getPassword(),
                        client.getDateOfBirth(),
                        hotelRoom
                )
        );

        return hotelRoomRepository.findAll();

    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
