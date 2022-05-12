package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.ClientAddressDto;
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
        return clientService.findAllClients().stream().map(
                client -> new ClientDto(
                        client.getEmail(),
                        client.getDateOfBirth(),
                        client.getBookedRooms(),
                        client.isAdult()
                        )
        ).collect(Collectors.toList());
    }

    @GetMapping(path = "all/info")
    public List<ClientAddressDto> findAllClientsWithAddress(){
        return clientService.findAllClients().stream().map(
                client -> new ClientAddressDto(
                        client.getEmail(),
                        client.getCountry(),
                        client.getCity(),
                        client.getStreet(),
                        client.getPostCode(),
                        client.getHouseNumber()
                )
        ).collect(Collectors.toList());
    }

    @PostMapping(path = "add")
    public Client addClient(@RequestBody ClientRequest clientRequest) {
        return clientService.addClient(clientRequest);
    }

    @PutMapping(path = "{clientId}/rooms/{roomId}/add")
    public List<HotelRoom> addClientToHotelRoom(@PathVariable Long clientId, @PathVariable Long roomId) {
        return clientService.addClientToHotelRoom(clientId, roomId);
    }

    @DeleteMapping(path = "{clientEmail}/delete")
    public String deleteClient(@PathVariable String clientEmail) {
        return clientService.deleteClient(clientEmail);
    }


}
