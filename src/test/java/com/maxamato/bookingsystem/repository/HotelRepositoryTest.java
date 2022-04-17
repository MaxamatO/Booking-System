package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HotelRepositoryTest {

    @Autowired
    HotelRepository hotelRepositoryUnderTest;

    @Test
    void findAllByCountry() {
        // given
        HotelRequest hotel = new HotelRequest(
                "Mariott",
                "warsaw",
                "Poland",
                Hotel.Stars.FOUR
        );
        hotelRepositoryUnderTest.save(new Hotel(
                hotel.getHotelName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getStars()
        ));

        // When
        List<Hotel> poland = hotelRepositoryUnderTest.findAllByCountry("Poland");

        // then
        assertThat(poland).isEqualTo(hotel);

    }
}