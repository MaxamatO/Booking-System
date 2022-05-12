package com.maxamato.bookingsystem.dtos;

import com.maxamato.bookingsystem.entities.HotelRoom;

import java.time.LocalDate;
import java.util.List;

public record ClientDto(String email, LocalDate dateOfBirth, List<HotelRoom> hotelRooms, boolean isAdult) {
}
