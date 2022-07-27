package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "management/api/v1/booking_system/client")
public class ClientManagementController {

    private final ClientService clientService;


    @DeleteMapping(path = "{clientEmail}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClient(@PathVariable String clientEmail) {
        return clientService.deleteClient(clientEmail);
    }

    @GetMapping(path = "all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ClientDto> findAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping(path = "{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOTELOWNER', 'CLIENT')")
    public ClientDto findClient(@PathParam("email") String email) {
        return clientService.findClientByEmail(email);
    }
}
