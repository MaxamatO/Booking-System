package com.maxamato.bookingsystem.services;

import com.maxamato.bookingsystem.dtos.ClientDto;
import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Client;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import com.maxamato.bookingsystem.repository.ClientRepository;
import com.maxamato.bookingsystem.repository.HotelRepository;
import com.maxamato.bookingsystem.repository.HotelRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Hotel addHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel(
                hotelRequest.getHotelName(),
                hotelRequest.getCity(),
                hotelRequest.getCountry(),
                hotelRequest.getStars(),
                hotelRequest.getIsAvailableOnSummer());

        hotelRepository.save(hotel);
        return hotel;
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

                                        // Adding hotelRoom.getClients() must be followed by another stream().map() and that gets pretty ugly
                                        // TODO: Make it more functional and readable


//                                        hotelRoom.getClients().stream().map(
//                                                client -> new ClientDto(
//                                                        client.getEmail(),
//                                                        client.getDateOfBirth(),
//                                                        client.isAdult(),
//                                                        client.getCountry(),
//                                                        client.getCity(),
//                                                        client.getStreet(),
//                                                        client.getPostCode(),
//                                                        client.getHouseNumber()
//                                                )
//                                        ).collect(Collectors.toList())

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

    public HotelRoom addHotelRoom(HotelRoomRequest hotelRoomRequest) {
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
        return hotelRoom;

    }

    public List<HotelRoomDto> getAllHotelRooms() {
        return hotelRoomRepository.findAll().stream().map(
                hotelRoom -> new HotelRoomDto(
                        hotelRoom.getIsAvailable(),
                        hotelRoom.getNumberOfBeds(),
                        hotelRoom.getHasPrivateToilet(),
                        hotelRoom.getNumberOfClients(),
                        hotelRoom.getClients().stream().map(
                                client -> new ClientDto(
                                        client.getEmail(),
                                        client.getDateOfBirth(),
                                        client.isAdult(),
                                        client.getCountry(),
                                        client.getCity(),
                                        client.getStreet(),
                                        client.getPostCode(),
                                        client.getHouseNumber()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    // Not implemented yet
    // Foreign key error
    public String deleteHotelRoom(Long roomId) {
        if (!hotelRoomRepository.existsById(roomId)) {
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).orElseThrow(() -> new IllegalStateException(
                new Exception("Room with provided id does not exist"))
        );
        if (hotelRoom.getClients().isEmpty()) {
            hotelRoomRepository.deleteById(roomId);
            hotelRepository.executeNumberOfRoomsUpdate();
            return "Deleted, no clients in it";
        }
        hotelRoom.emptyClients();
        hotelRoomRepository.deleteById(roomId);
        hotelRepository.executeNumberOfRoomsUpdate();
        return "Deleted";
    }


    // Not implemented yet
    // Foreign key error
    public String deleteHotel(Long id) {
//        hotelRepository.deleteById(id);

        return "Deleted";
    }
}
