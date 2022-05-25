package com.maxamato.bookingsystem.repository.custom.hotelRepos;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomizedHotelRepositoryImpl implements CustomizedHotelRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void executeNumberOfRoomsUpdate() {
        String sql = "UPDATE hotels SET number_of_rooms = (SELECT COUNT (hotel_room.hotel_id) FROM hotel_room  WHERE hotels.id = hotel_room.hotel_id);";
        final Query hotelTypedQuery = entityManager.createNativeQuery(sql);
        hotelTypedQuery.executeUpdate();
    }
}
