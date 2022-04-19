package com.maxamato.bookingsystem.repo;

import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// TODO: Tests not working (no idea why:)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "test/java/resources/application-context.xml")
@DataJpaTest
public class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testSaving(){

//        hotelRepository.save(new Hotel(
//           "sd",
//           "asdad",
//           "poland",
//           4
//        ));

    }

}
