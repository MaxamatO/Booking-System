package com.maxamato.bookingsystem.services;


import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HotelServiceTest {

    @Autowired
    HotelService underTest;

    private Hotel hotel;
    private HotelRoom hotelRoom;

    @BeforeEach
    void setUp() {

        hotel = new Hotel();
        hotel.setHotelName("Marriott");
        hotel.setId(1L);
        hotel.setCity("Warsaw");
        hotel.setCountry("Poland");
        hotel.setIsAvailableOnSummer(true);
        hotel.setStars(4);

        hotelRoom = new HotelRoom();
        hotelRoom.setId(1L);
        hotelRoom.setHasPrivateToilet(true);
        hotelRoom.setIsAvailable(true);
        hotelRoom.setNumberOfBeds(2);
        hotel.addHotelRoom(hotelRoom);
    }

    @Test
    void addHotel() {
        // given
        HotelRequest hotelRequest = new HotelRequest(
                hotel.getHotelName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getStars(),
                hotel.getIsAvailableOnSummer()
        );
        // when
        HotelDto hotelDto = underTest.addHotel(hotelRequest);

        // then
        assertEquals(hotel.getHotelName(), hotelDto.getHotelName());
        assertEquals(hotel.getCity(), hotelDto.getCity());
        assertEquals(hotel.getCountry(), hotelDto.getCountry());
        assertEquals(hotel.getStars(), hotelDto.getStars());
        assertEquals(hotel.getIsAvailableOnSummer(), hotelDto.getIsAvailableOnSummer());
    }

    @Test
    void getHotels() {
    }

    @Test
    void getHotelsFrom() {
    }

    @Test
    void addHotelRoom() {
        // given
        HotelRequest hotelRequest = new HotelRequest(
                hotel.getHotelName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getStars(),
                hotel.getIsAvailableOnSummer()
        );
        // when
        HotelDto hotelDto = underTest.addHotel(hotelRequest);
        HotelRoomRequest hotelRoomRequest = new HotelRoomRequest(
                hotelRoom.getNumberOfBeds(),
                hotelRoom.getHasPrivateToilet(),
                hotelRoom.getIsAvailable(),
                1L
        );
        // when
        HotelRoomDto hotelRoomDto = underTest.addHotelRoom(hotelRoomRequest);
        // then
        assertEquals(hotelRoom.getNumberOfBeds(), hotelRoomDto.getNumberOfBeds());
        assertEquals(hotelRoom.getHasPrivateToilet(), hotelRoomDto.getHasPrivateToilet());
        assertEquals(hotelRoom.getIsAvailable(),hotelRoomDto.getIsAvailable());
        assertEquals(hotel.getId(), hotelRoomDto.getHotelId());
    }

    @Test
    void getHotelRoomById() {
    }

    @Test
    void getAllHotelRooms() {
    }
}