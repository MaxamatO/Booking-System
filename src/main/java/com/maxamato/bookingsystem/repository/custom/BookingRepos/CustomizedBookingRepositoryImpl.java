package com.maxamato.bookingsystem.repository.custom.BookingRepos;

import com.maxamato.bookingsystem.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CustomizedBookingRepositoryImpl implements CustomizedBookingRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Booking> findAllBookingsByClientId(Long clientId) {
        String sql = String.format(
                        "select * from booking" +
                        " inner join client" +
                        " on booking.client_id = client.id " +
                        "where client.id = %d;",
                clientId);
        final Query bookingTypedQuery = entityManager.createNativeQuery(sql, Booking.class);
        return bookingTypedQuery.getResultList();
    }

    // TODO: Idea is to get List<Client> so it can be displayed as clients[] in a response @http://host/api/v1/booking_system/hotel/room/{id}
//    @Override
//    public List<Long> findAllClientsIntoDto(Long roomId) {
//        String sql = String.format("select * from booking where booking.hotel_room_id = %d;", roomId);
//        final Query clientTypedQuery = entityManager.createNativeQuery(sql);
//        return clientTypedQuery.getResultList();
//    }

    @Override
    public void deleteAllByHotelRoomId(List<Long> ids){
        String sql = "delete from Booking where Booking.hotel_room_id in :ids";
        Query q = entityManager.createNativeQuery(sql);
        q.setParameter("ids", ids);
        q.executeUpdate();
    }

    @Override
    public void deleteByHotelRoomId(Long id) {
        String sql = "delete from Booking where Booking.hotel_room_id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByClientId(Long id) {
        String sql = "delete from Booking where Booking.client_id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
