package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/booking_system/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping(path = "all")
    public List<Client> findAllClients(){
        return clientService.findAllClients();
    }

    @PostMapping(path = "add")
    public Client addClient(@RequestBody ClientRequest clientRequest) {
        return clientService.addClient(clientRequest);
    }

    // NOT WORKING
    @PutMapping(path = "{clientId}/rooms/{roomId}/add")
    public List<HotelRoom> addClientToHotelRoom(@PathVariable Long clientId, @PathVariable Long roomId){
        return clientService.addClientToHotelRoom(clientId, roomId);
    }



}
