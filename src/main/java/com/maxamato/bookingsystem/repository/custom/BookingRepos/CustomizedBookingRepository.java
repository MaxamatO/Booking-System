package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;

import java.util.List;

public interface CustomizedBookingRepository {

    List<Booking> findAllBookingsByClientId(Long clientId);

    void deleteAllByHotelRoomId(List<Long> ids);

    void deleteByHotelRoomId(Long id);

    void deleteByClientId(Long id);

}
