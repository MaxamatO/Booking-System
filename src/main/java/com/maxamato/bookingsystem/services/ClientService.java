package com.maxamato.bookingsystem.services;


import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Setter
@Getter
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public Client addClient(ClientRequest clientRequest) {
        Client client = new Client(
                clientRequest.getEmail(),
                clientRequest.getPassword(),
                clientRequest.getDateOfBirth()
        );
        clientRepository.save(client);
        return client;
    }
}
