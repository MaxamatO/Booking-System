package com.maxamato.bookingsystem.services;


import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void addClient(ClientRequest clientRequest) {
        clientRepository.save(new Client(
                clientRequest.getEmail(),
                clientRequest.getPassword(),
                clientRequest.getDateOfBirth()
        ));
    }
}
