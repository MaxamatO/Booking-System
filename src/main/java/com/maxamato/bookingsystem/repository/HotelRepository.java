package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    public List<Hotel> findAllByCountry(String country);

    @Query(nativeQuery = true, value = "update hotels h SET number_of_rooms = (SELECT COUNT(r.hotel_id) FROM hotel_room r WHERE r.hotel_id = h.hotel_id);")
    public void executeUpdate();

}
