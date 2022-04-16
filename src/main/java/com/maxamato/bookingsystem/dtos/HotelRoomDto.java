package com.maxamato.bookingsystem.dtos;

public record HotelRoomDto(String hotelName,
                           String city,
                           String country,
                           Integer stars,
                           Integer numberOfRooms,
                           Boolean isAvailableOnSummer) {
}
