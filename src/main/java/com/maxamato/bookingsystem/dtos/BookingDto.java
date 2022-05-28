package com.maxamato.bookingsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BookingDto {

    private ClientDto client;
    private HotelRoomDto hotelRoomDto;



}
