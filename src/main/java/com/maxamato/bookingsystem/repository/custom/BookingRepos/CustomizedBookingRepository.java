package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;

import java.util.List;

public interface CustomizedBookingRepository {

    List<Booking> findAllBookingsByClientId(Long clientId);

//    List<Long> findAllClientsIntoDto(Long roomId);

}
