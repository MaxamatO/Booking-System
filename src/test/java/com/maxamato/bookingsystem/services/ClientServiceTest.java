package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.BookingDto;
import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.BookingRequest;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class ClientServiceTest {


    @Autowired
    private ClientService underTest;

    @Autowired
    private HotelService hotelService;

    private Hotel hotel;
    private HotelRoom hotelRoom;
    private Client client;
    private Booking booking;

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

        client = new Client();
        client.setId(1L);
        client.setEmail("defaultclient@gmail.com");
        client.setCity("Paris");
        client.setCountry("France");
        client.setDateOfBirth(LocalDate.of(2000, 3, 15));
        client.setPostCode("23-444");
        client.setPassword("Password12!");
        client.setStreet("Baker Street");
        client.setHouseNumber(44);

        booking = new Booking();
        booking.setClient(client);
        booking.setId(1L);
        booking.setHotelRoom(hotelRoom);
        booking.setAccommodationDate(LocalDate.now());
        booking.setEvictionDate(LocalDate.of(2022, 7, 30));

    }

    @Test
    @Disabled
    void canAddClient() {
        // given
        ClientRequest clientRequest = new ClientRequest(
                client.getEmail(),
                client.getPassword(),
                client.getDateOfBirth(),
                client.getPostCode(),
                client.getStreet(),
                client.getCountry(),
                client.getCity(),
                client.getHouseNumber()
        );
        // when
        ClientDto clientDto = underTest.addClient(clientRequest);

        // then
        assertEquals(clientRequest.getEmail(), clientDto.getEmail());
        assertEquals(clientRequest.getCity(), clientDto.getCity());
        assertEquals(clientRequest.getCountry(), clientDto.getCountry());
        assertEquals(clientRequest.getDateOfBirth(), clientDto.getDateOfBirth());
        assertEquals(clientRequest.getStreet(), clientDto.getStreet());
        assertEquals(clientRequest.getPostCode(), clientDto.getPostCode());
        assertEquals(clientRequest.getHouseNumber(), clientDto.getHouseNumber());
    }

    @Test
    @Disabled
    void deleteClient() {
    }

    @Test
    void canAddClientToHotelRoom() {
        // given
        ClientRequest clientRequest = new ClientRequest(
                client.getEmail(),
                client.getPassword(),
                client.getDateOfBirth(),
                client.getPostCode(),
                client.getStreet(),
                client.getCountry(),
                client.getCity(),
                client.getHouseNumber()
        );
        underTest.addClient(clientRequest);
        BookingRequest bookingRequest = new BookingRequest(
                booking.getClient().getId(),
                booking.getHotelRoom().getId(),
                booking.getAccommodationDate(),
                booking.getEvictionDate()

                );
        hotelService.addHotel(
                new HotelRequest(
                        hotel.getHotelName(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getStars(),
                        hotel.getIsAvailableOnSummer()
                )
        );

        hotelService.addHotelRoom(
                new HotelRoomRequest(
                        hotelRoom.getNumberOfBeds(),
                        hotel.getId()
                )
        );
        // when

        BookingDto bookingDto = underTest.addClientToHotelRoom(bookingRequest);

        // then
        assertEquals(booking.getClient().getEmail(), bookingDto.getClient().getEmail());

    }



    @Test
    void findClientByEmail() {
        // given
        underTest.addClient(
                new ClientRequest(
                        client.getEmail(),
                        client.getPassword(),
                        client.getDateOfBirth(),
                        client.getPostCode(),
                        client.getStreet(),
                        client.getCountry(),
                        client.getCity(),
                        client.getHouseNumber()
                )
        );
        // when
        ClientDto clientTest = underTest.findClientByEmail(client.getEmail());
        // then
        Assertions.assertNotNull(client);
        assertEquals(client.getEmail(), clientTest.getEmail());
        assertEquals(client.getCity(), clientTest.getCity());
        assertEquals(client.getCountry(), clientTest.getCountry());
        assertEquals(client.getHouseNumber(), clientTest.getHouseNumber());
        assertEquals(client.getStreet(), clientTest.getStreet());
        assertEquals(client.getPostCode(), clientTest.getPostCode());
        assertEquals(client.getDateOfBirth(), clientTest.getDateOfBirth());
    }

    static Stream<Arguments> findClientByEmailData(){
        ClientServiceTest clientServiceTest = new ClientServiceTest();
        return Stream.of(
                Arguments.of(clientServiceTest.client.getEmail(), true),
                Arguments.of("notexistingemail@gmail.com", true)
        );
    }
}