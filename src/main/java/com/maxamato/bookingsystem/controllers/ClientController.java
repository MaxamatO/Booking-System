package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/booking_system/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    private String addClient(ClientRequest clientRequest){
        clientService.addClient(clientRequest);
        return "added";
    }
}
