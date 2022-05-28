package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;

import java.util.List;

public interface CustomizedBookingRepository {

    List<Booking> findAllBookingsByClientId(Long clientId);

    List<Client> findAllClientsIntoDto(Long roomId);
}
