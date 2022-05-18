package com.maxamato.bookingsystem.dtos;

import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;

import java.util.List;

public record HotelDto(String hotelName, String city, String country,
                       Integer stars, int numberOfRooms, List<HotelRoom> hotelRooms) {



}
