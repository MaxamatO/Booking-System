package com.maxamato.bookingsystem.repository.custom.hotelRoomRepos;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomizedHotelRoomRepositoryImpl implements CustomizedHotelRoomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void executeNumberOfClientsUpdate() {
        String sql = "update hotel_room hr set number_of_clients = (select count(b.hotel_room_id) from booking b where b.hotel_room_id = hr.id);";
        final Query hotelTypedQuery = entityManager.createNativeQuery(sql);
        hotelTypedQuery.executeUpdate();
    }

}
