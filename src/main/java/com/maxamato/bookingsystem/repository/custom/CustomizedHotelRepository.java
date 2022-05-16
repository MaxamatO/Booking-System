package com.maxamato.bookingsystem.repository.custom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;

public interface CustomizedHotelRepository {

    String executeNumberOfRoomsUpdate();
}
