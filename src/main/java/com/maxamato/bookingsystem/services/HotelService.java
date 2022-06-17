package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import com.maxamato.bookingsystem.repository.BookingRepository;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Setter
@Getter
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final ClientRepository clientRepository;
    private final BookingRepository bookingRepository;

    public HotelDto addHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel(
                hotelRequest.getHotelName(),
                hotelRequest.getCity(),
                hotelRequest.getCountry(),
                hotelRequest.getStars(),
                hotelRequest.getIsAvailableOnSummer());

        hotelRepository.save(hotel);
        return new HotelDto(
                hotel.getHotelName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getStars(),
                hotel.getNumberOfRooms(),
                hotel.getIsAvailableOnSummer()
        );
    }

    public HotelDto getHotel(Long id) {
        return hotelRepository.findById(id).map(
                hotel -> new HotelDto(
                        hotel.getHotelName(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getStars(),
                        hotel.getNumberOfRooms(),
                        hotel.getHotelRooms().stream().map(
                                hotelRoom -> new HotelRoomDto(
                                        hotelRoom.getIsAvailable(),
                                        hotelRoom.getNumberOfBeds(),
                                        hotelRoom.getHasPrivateToilet(),
                                        hotelRoom.getNumberOfClients()
                                )
                        ).collect(Collectors.toList())
                )).orElseThrow();
    }

    public List<HotelDto> getHotels() {
        return hotelRepository.findAll().stream().map(
                hotel -> new HotelDto(
                        hotel.getHotelName(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getStars(),
                        hotel.getNumberOfRooms(),
                        hotel.getHotelRooms().stream().map(
                                hotelRoom -> new HotelRoomDto(
                                        hotelRoom.getIsAvailable(),
                                        hotelRoom.getNumberOfBeds(),
                                        hotelRoom.getHasPrivateToilet(),
                                        hotelRoom.getNumberOfClients()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    public List<HotelDto> getHotelsFrom(String country) {
        return hotelRepository.findAllByCountry(country).stream().map(
                hotel -> new HotelDto(
                        hotel.getHotelName(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getStars(),
                        hotel.getNumberOfRooms(),
                        hotel.getHotelRooms().stream().map(
                                hotelRoom -> new HotelRoomDto(
                                        hotelRoom.getIsAvailable(),
                                        hotelRoom.getNumberOfBeds(),
                                        hotelRoom.getHasPrivateToilet(),
                                        hotelRoom.getNumberOfClients()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    public HotelRoomDto addHotelRoom(HotelRoomRequest hotelRoomRequest) {
        Hotel hotel = hotelRepository.findById(hotelRoomRequest.getHotelId()).orElseThrow(
                () -> new IllegalStateException(new Exception(
                        "Hotel with provided id does not exist."
                )));
        HotelRoom hotelRoom = new HotelRoom(
                hotelRoomRequest.getNumberOfBeds(),
                hotelRoomRequest.getHasPrivateToilet(),
                hotelRoomRequest.getIsAvailable()
        );
        hotel.addHotelRoom(hotelRoom);
        hotelRoomRepository.save(hotelRoom);
        hotelRepository.executeNumberOfRoomsUpdate();
        return new HotelRoomDto(
                hotelRoom.getIsAvailable(),
                hotelRoom.getNumberOfBeds(),
                hotelRoom.getHasPrivateToilet(),
                hotelRoom.getNumberOfClients(),
                hotel.getId()

        );

    }

    public HotelRoomDto getHotelRoomById(Long roomId){
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).orElseThrow(() -> new IllegalStateException(
                new Exception("Room with provided id does not exist")
        ));
        List<Long> clientsIds = new ArrayList<>(bookingRepository.findAllClientsIntoDto(roomId));
        List<Client> clients = new ArrayList<>(clientRepository.findAllById(clientsIds));
        List<ClientDto> clientDtos = clients.stream().map(
                client -> new ClientDto(
                        client.getEmail(),
                        client.getDateOfBirth()
                )
        ).collect(Collectors.toList());
        return new HotelRoomDto(
                clientDtos,
                hotelRoom.getNumberOfBeds(),
                hotelRoom.getNumberOfClients(),
                hotelRoom.getId()
        );

    }

    public List<HotelRoomDto> getAllHotelRooms() {
        return hotelRoomRepository.findAll().stream().map(
                hotelRoom -> new HotelRoomDto(
                        hotelRoom.getId(),
                        hotelRoom.getIsAvailable()
                )
        ).collect(Collectors.toList());
    }


    public String deleteHotelRoom(Long roomId) {
        if(!hotelRoomRepository.existsById(roomId)){
            throw new IllegalStateException(new Exception(
                    "Hotel room with provided id can't be deleted - doesn't exist."
            ));
        }
        bookingRepository.deleteByHotelRoomId(roomId);
        hotelRoomRepository.deleteById(roomId);
        return String.format("Hotel room with id: %s got deleted.", roomId);
    }



    public String deleteHotel(Long id) {
        if(!hotelRepository.existsById(id)){
            throw new IllegalStateException(new Exception(
                    "Hotel with provided id can't be deleted - doesn't exist."
            ));
        }
        List<HotelRoom> hotelRooms = hotelRoomRepository.findByHotelId(id);
        List<Long> ids = hotelRooms.stream().map(HotelRoom::getId).collect(Collectors.toList());
        bookingRepository.deleteAllByHotelRoomId(ids);
        hotelRepository.deleteById(id);
        return String.format("Hotel with ID: %s got deleted", id);
    }
}
