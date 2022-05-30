package com.maxamato.bookingsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maxamato.bookingsystem.entities.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRoomDto {

    private Long id;
    private Boolean isAvailable;
    private Integer numberOfBeds;
    private Boolean hasPrivateToilet;
    private Integer numberOfClients;
    private List<BookingDto> bookings;

    public HotelRoomDto(Boolean isAvailable, int numberOfBeds, Boolean hasPrivateToilet, int numberOfClients, List<BookingDto> bookings) {
        this.isAvailable = isAvailable;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet = hasPrivateToilet;
        this.numberOfClients = numberOfClients;
        this.bookings = bookings;
    }

    public HotelRoomDto(Boolean isAvailable, int numberOfBeds, Boolean hasPrivateToilet, int numberOfClients) {
        this.isAvailable = isAvailable;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet = hasPrivateToilet;
        this.numberOfClients = numberOfClients;
    }

    public HotelRoomDto(List<ClientDto> clientsDtos, int numberOfBeds, int numberOfClients){
        this.numberOfClients = numberOfClients;
        this.numberOfBeds = numberOfBeds;
    }

    // TODO: Number of clients update only after another call of executeNumberOfClients
    public HotelRoomDto(Long Id){
        this.id = Id;
//        this.numberOfClients = numberOfClients;
    }


}
