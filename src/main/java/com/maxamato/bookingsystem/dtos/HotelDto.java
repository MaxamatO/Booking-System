package com.maxamato.bookingsystem.dtos;

import com.maxamato.bookingsystem.entities.Hotel;

public record HotelDto(String hotelName, String city, String country,
                       Integer stars) {



}
