package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.repository.custom.hotelRoomRepos.CustomizedHotelRoomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long>, CustomizedHotelRoomRepository {

    @Query(value = "SELECT * FROM hotel_room WHERE hotel_id =?1", nativeQuery = true)
    List<HotelRoom> findByHotelId(Long id);

}
