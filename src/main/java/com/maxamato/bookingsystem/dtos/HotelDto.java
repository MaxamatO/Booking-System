package com.maxamato.bookingsystem.dtos;

import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDto {

    private String hotelName;
    private String city;
    private String country;
    private Integer stars;
    private int numberOfRooms;
    private List<HotelRoomDto> hotelRooms;

    public HotelDto(String hotelName, String city, String country,
                    Integer stars, int numberOfRooms, List<HotelRoomDto> hotelRooms){
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
        this.numberOfRooms = numberOfRooms;
        this.hotelRooms = hotelRooms;
    }


}
