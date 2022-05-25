package com.maxamato.bookingsystem.repository.custom.hotelRoomRepos;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomizedHotelRoomRepositoryImpl implements CustomizedHotelRoomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void executeNumberOfClientsUpdate() {
        String sql = "update hotel_room hr SET number_of_clients = (SELECT COUNT(c.hotel_room_id) FROM hotel_room_client c WHERE c.hotel_room_id = hr.id);";
        final Query hotelTypedQuery = entityManager.createNativeQuery(sql);
        hotelTypedQuery.executeUpdate();
    }

}
