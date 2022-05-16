package com.maxamato.bookingsystem.repository.custom;

import com.maxamato.bookingsystem.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CustomizedHotelRepositoryImpl implements CustomizedHotelRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public String executeUpdate() {
        String sql = "update Hotel h SET numberOfRooms = (SELECT COUNT(r.hotel.hotelId) FROM HotelRoom r WHERE r.hotel.hotelId = h.hotelId)";
        final Query hotelTypedQuery = entityManager.createQuery(sql);
        hotelTypedQuery.executeUpdate();
        return "updated";
    }
}
