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
//        System.out.println(this.clients.isEmpty());
    }

    // TODO: Number of clients update only after another call of executeNumberOfClients
    public HotelRoomDto(Long Id){
        this.id = Id;
//        this.numberOfClients = numberOfClients;
    }


}
