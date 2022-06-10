package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.repository.BookingRepository;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Entity;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private HotelRoomRepository hotelRoomRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private BookingRepository bookingRepository;
    private ClientService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClientService(clientRepository, hotelRoomRepository, hotelRepository, bookingRepository);
    }

    @Test
    void canFindAllClients() {
        // when
        underTest.findAllClients();
        // then
        verify(clientRepository).deleteAll();
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
    void findClient() {
    }

    @Test
    @Disabled
    void deleteClient() {
    }
}