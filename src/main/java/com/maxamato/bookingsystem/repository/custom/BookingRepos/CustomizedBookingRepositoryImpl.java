package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;
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
        final Query clientTypedQuery = entityManager.createNativeQuery(sql, Booking.class);
        return clientTypedQuery.getResultList();

    }
}
