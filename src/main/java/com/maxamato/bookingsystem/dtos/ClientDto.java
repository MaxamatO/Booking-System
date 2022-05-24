package com.maxamato.bookingsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private String email;
    private LocalDate dateOfBirth;
    private List<HotelRoomDto> hotelRooms;
    private Boolean isAdult;
    private String country;
    private String city;
    private String street;
    private String postCode;
    private Integer houseNumber;

    public ClientDto(String email, LocalDate dateOfBirth,
                     List<HotelRoomDto> hotelRooms, boolean isAdult,
                     String country, String city, String street,
                     String postCode, int houseNumber) {

        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hotelRooms = hotelRooms;
        this.isAdult = isAdult;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.houseNumber = houseNumber;
    }

    //ClientAddressDto
    public ClientDto(String email, LocalDate dateOfBirth,
                     boolean isAdult,
                     String country, String city, String street,
                     String postCode, int houseNumber) {

        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = isAdult;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.houseNumber = houseNumber;
    }

    // ClientRoomsDto
    public ClientDto(String email, LocalDate dateOfBirth, List<HotelRoomDto> hotelRooms, boolean isAdult){
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hotelRooms = hotelRooms;
        this.isAdult = isAdult;
    }

}


