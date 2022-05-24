package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/booking_system/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping(path = "all")
    public List<ClientDto> findAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping(path = "all/info")
    public List<ClientDto> findAllClientsWithAddress(){
        return clientService.findAllClientsAddress();
    }

    @GetMapping(path = "all/rooms")
    public List<ClientDto> findAllClientsWithRooms(){
        return clientService.findAllClientsRooms();
    }

    @PostMapping(path = "add")
    public ClientDto addClient(@RequestBody ClientRequest clientRequest) {
        return clientService.addClient(clientRequest);
    }

    @PutMapping(path = "{clientId}/rooms/{roomId}/add")
    public ClientDto addClientToHotelRoom(@PathVariable Long clientId, @PathVariable Long roomId) {
        return clientService.addClientToHotelRoom(clientId, roomId);
    }

    @DeleteMapping(path = "{clientEmail}/delete")
    public String deleteClient(@PathVariable String clientEmail) {
        return clientService.deleteClient(clientEmail);
    }


}
