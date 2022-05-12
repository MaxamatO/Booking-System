package com.maxamato.bookingsystem.dtos;


import com.maxamato.bookingsystem.entities.HotelRoom;

import java.util.List;

public record ClientRoomsDto(String email, boolean isAdult, List<HotelRoom> hotelRooms) {
}
