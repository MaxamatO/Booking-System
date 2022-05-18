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
    public String executeNumberOfRoomsUpdate() {
        String sql = "UPDATE hotels SET number_of_rooms = (SELECT COUNT (hotel_room.hotel_id) FROM hotel_room  WHERE hotels.id = hotel_room.hotel_id);";
        final Query hotelTypedQuery = entityManager.createNativeQuery(sql);
        hotelTypedQuery.executeUpdate();
        return "updated";
    }
}
