package com.maxamato.bookingsystem.repository.impl;


import com.maxamato.bookingsystem.entities.Hotel;

import com.maxamato.bookingsystem.repository.customs.CustomizedHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

@Component
public class HotelRepositoryImpl implements CustomizedHotelRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void executeUpdate() {
        String sql = "update hotels h SET number_of_rooms = (SELECT COUNT(r.hotel_id) FROM hotel_room r WHERE r.hotel_id = h.hotel_id);";
        final TypedQuery<Hotel> query = entityManager.createQuery(sql, Hotel.class);
        query.executeUpdate();
    }
}
