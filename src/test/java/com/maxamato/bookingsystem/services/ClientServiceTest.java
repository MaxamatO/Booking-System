package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.BookingDto;
import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.entities.Booking;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.BookingRequest;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.BookingRepository;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;
    @MockBean
    private HotelRoomRepository hotelRoomRepository;
    @MockBean
    private HotelRepository hotelRepository;
    @MockBean
    private BookingRepository bookingRepository;
    @InjectMocks
    private ClientService underTest;


    private Hotel hotel;
    private HotelRoom hotelRoom;
    private Client client;
    private Booking booking;

    @BeforeEach
    void setUp() {
        underTest = new ClientService(clientRepository, hotelRoomRepository, hotelRepository, bookingRepository);

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
        client.setEmail("test@gmail.com");
        client.setCity("Paris");
        client.setCountry("France");
        client.setDateOfBirth(LocalDate.of(2000, 3, 15));
        client.setPostCode("23-444");
        client.setPassword("Password12!");
        client.setHouseNumber(44);

        booking = new Booking();
        booking.setClient(client);
        booking.setId(1L);
        booking.setHotelRoom(hotelRoom);
        booking.setAccommodationDate(LocalDate.now());
        booking.setEvictionDate(LocalDate.of(2022, 7, 30));

    }

    @Test
    void canFindAllClients() {
        // when
        underTest.findAllClients();
        // then
        verify(clientRepository).findAll();
    }

    @Test
    void canAddClient() {
        // given
        final String email = "test@gmail.com";
        ClientRequest clientRequest = new ClientRequest(
                email,
                "Password12!",
                LocalDate.of(2000, 3, 15),
                "France",
                "23-444",
                "Paris",
                "something",
                44
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
    @Transactional
    void canAddClientToHotelRoom() {
        // given
        BookingRequest bookingRequest = new BookingRequest(booking.getClient().getId(), booking.getHotelRoom().getId(),
                booking.getAccommodationDate(), booking.getEvictionDate()
                );
        // when
        clientRepository.save(client);
        hotelRepository.save(hotel);
        hotelRoomRepository.save(hotelRoom);
        BookingDto bookingDto = underTest.addClientToHotelRoom(bookingRequest);

        // then
//        Client client = clientRepository.findByEmail(bookingDto.getClient().getEmail()).get();
//        assertEquals(bookingRequest.getClientId(), client.getId());
    }

    @Test
    void findClientByEmail() {
        // given
        String email = "test@gmail.com";
        Mockito.when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));

        // when
        ClientDto clientTest = underTest.findClientByEmail(email);

        // then
        Assertions.assertNotNull(client);
        assertEquals(client.getEmail(), clientTest.getEmail());

    }
}