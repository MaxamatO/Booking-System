package com.maxamato.bookingsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRoomDto {

    private Long id;
    private Boolean isAvailable;
    private Integer numberOfBeds;
    private Boolean hasPrivateToilet;
    private Integer numberOfClients;
    private Long hotelId;
    private List<ClientDto> clients;
    private Integer totalAmountOfNights;


    public HotelRoomDto(Boolean isAvailable, int numberOfBeds, Boolean hasPrivateToilet, int numberOfClients) {
        this.isAvailable = isAvailable;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet = hasPrivateToilet;
        this.numberOfClients = numberOfClients;
    }

    public HotelRoomDto(List<ClientDto> clients, int numberOfBeds, int numberOfClients, Long hotelId){
        this.clients = clients;
        this.numberOfClients = numberOfClients;
        this.numberOfBeds = numberOfBeds;
        this.hotelId = hotelId;
    }

    // TODO: Number of clients update only after another call of executeNumberOfClients
    public HotelRoomDto(Long Id, Integer totalAmountOfNights){
        this.id = Id;
        this.totalAmountOfNights = totalAmountOfNights;
    }


    public HotelRoomDto(Long hotelId, Boolean isAvailable) {
        this.hotelId = hotelId;
        this.isAvailable = isAvailable;
    }

    public HotelRoomDto(Boolean isAvailable, Integer numberOfBeds, Boolean hasPrivateToilet, Integer numberOfClients, Long hotelId) {
        this.isAvailable = isAvailable;
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet = hasPrivateToilet;
        this.numberOfClients = numberOfClients;
         this.hotelId = hotelId;
    }
}
