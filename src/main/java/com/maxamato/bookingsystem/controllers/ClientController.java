package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.BookingDto;
import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.entities.requests.BookingRequest;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/booking_system/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping(path = "all")
    public List<ClientDto> findAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping(path = "{clientId}")
    public ClientDto findClient(@PathParam("email") String email) {
        return clientService.findClientByEmail(email);
    }

    @GetMapping(path = "all/info")
    public List<ClientDto> findAllClientsWithAddress() {
        return clientService.findAllClientsAddress();
    }

    @GetMapping(path = "{clientId}/all/rooms")
    public ClientDto getBookingsForAClient(@PathVariable Long clientId) {
        return clientService.getBookingsForAClient(clientId);
    }

    @PostMapping(path = "add")
    public ClientDto addClient(@RequestBody ClientRequest clientRequest) {
        return clientService.addClient(clientRequest);
    }

    @PutMapping(path = "/rooms/book")
    public BookingDto addClientToHotelRoom(@RequestBody BookingRequest bookingRequest) {
        return clientService.addClientToHotelRoom(bookingRequest);
    }

    @DeleteMapping(path = "{clientEmail}/delete")
    public String deleteClient(@PathVariable String clientEmail) {
        return clientService.deleteClient(clientEmail);
    }


}
