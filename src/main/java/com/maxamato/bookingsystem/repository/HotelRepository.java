package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.repository.customs.CustomizedHotelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, CustomizedHotelRepository {

    public List<Hotel> findAllByCountry(String country);
    public void executeUpdate();

}
