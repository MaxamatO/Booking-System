package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.repository.custom.BookingRepos.CustomizedBookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, CustomizedBookingRepository {
    List<Booking> findAllBookingsByClientId(Long clientId);

    @Query(value = "select * from booking where booking.hotel_room_id = ?1", nativeQuery = true)
    List<Long> findAllClientsIntoDto(Long roomId);

    @Query(value = "SELECT COUNT(client_id) FROM Booking  WHERE Booking.client_id=?1 AND Booking.hotel_room_id=?2", nativeQuery = true)
    long getCountOfClients(Long clientId, Long roomId);
}
