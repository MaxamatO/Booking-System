package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "management/api/v1/booking_system/client")
public class ClientManagementController {

    private final ClientService clientService;


    @DeleteMapping(path = "{clientEmail}/delete")
    public String deleteClient(@PathVariable String clientEmail) {
        return clientService.deleteClient(clientEmail);
    }

    @GetMapping(path = "all")
    public List<ClientDto> findAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping(path = "{clientId}")
    public ClientDto findClient(@PathParam("email") String email) {
        return clientService.findClientByEmail(email);
    }
}
