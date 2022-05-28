package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.repository.custom.BookingRepos.CustomizedBookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, CustomizedBookingRepository {
    List<Booking> findAllBookingsByClientId(Long clientId);
}
