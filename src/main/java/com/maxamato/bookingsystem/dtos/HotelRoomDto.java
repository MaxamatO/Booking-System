package com.maxamato.bookingsystem.dtos;

import com.maxamato.bookingsystem.entities.Client;

import java.util.List;

public record HotelRoomDto(Boolean isAvailable,
                           int numberOfBeds,
                           Boolean hasPrivateToilet,
                           int numberOfClients,
                           List<Client> clients) {
}
