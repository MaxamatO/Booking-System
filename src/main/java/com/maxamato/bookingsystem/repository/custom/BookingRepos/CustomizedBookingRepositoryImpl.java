package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomizedBookingRepositoryImpl implements CustomizedBookingRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Booking> findAllBookingsByClientId(Long clientId) {
        String sql = String.format("select * from client, booking where booking.client_id = %d;", clientId);
        final Query bookingTypedQuery = entityManager.createNativeQuery(sql, Booking.class);
        return bookingTypedQuery.getResultList();
    }

    @Override
    public List<Client> findAllClientsIntoDto(Long roomId) {
        String sql = String.format("select * from client, booking where booking.hotel_room_id = %d;", roomId);
        final Query clientTypedQuery = entityManager.createNativeQuery(sql, Client.class);
        return clientTypedQuery.getResultList();
    }
}
