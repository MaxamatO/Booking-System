package com.maxamato.bookingsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDto {

    private String hotelName;
    private String city;
    private String country;
    private Integer stars;
    private Integer numberOfRooms;
    private List<HotelRoomDto> hotelRooms;
    private Boolean isAvailableOnSummer;

    public HotelDto(String hotelName, String city, String country,
                    Integer stars, int numberOfRooms, List<HotelRoomDto> hotelRooms){
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
        this.numberOfRooms = numberOfRooms;
        this.hotelRooms = hotelRooms;
    }

    public HotelDto(String hotelName, String city, String country,
                    Integer stars, int numberOfRooms,
                    Boolean isAvailableOnSummer){
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
        this.numberOfRooms = numberOfRooms;
        this.isAvailableOnSummer = isAvailableOnSummer;
    }


}
