package com.maxamato.bookingsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private String email;
    private LocalDate dateOfBirth;
    private List<BookingDto> bookings;
    private Boolean isAdult;
    private String country;
    private String city;
    private String street;
    private String postCode;
    private Integer houseNumber;
    private List<HotelRoomDto> hotelRooms;

    //ClientAddressDto
    public ClientDto(String email, LocalDate dateOfBirth,
                     boolean isAdult,
                     String country, String postCode, String city,
                     String street, int houseNumber) {

        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = isAdult;
        this.city = city;
        this.country = country;
        this.street = street;
        this.postCode = postCode;
        this.houseNumber = houseNumber;
    }



    public ClientDto(String email,LocalDate dateOfBirth, List<HotelRoomDto> hotelRooms){
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hotelRooms = hotelRooms;

    }

    // Client in Booking
    public ClientDto(String email,LocalDate dateOfBirth) {
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}


