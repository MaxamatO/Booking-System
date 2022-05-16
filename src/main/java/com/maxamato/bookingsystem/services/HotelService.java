package com.maxamato.bookingsystem.services;

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

@Service
@AllArgsConstructor
@Setter
@Getter
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final ClientRepository clientRepository;

    public Hotel addHotel(HotelRequest hotelRequest){
        Hotel hotel = new Hotel(
                hotelRequest.getHotelName(),
                hotelRequest.getCity(),
                hotelRequest.getCountry(),
                hotelRequest.getStars(),
                hotelRequest.getIsAvailableOnSummer());

        hotelRepository.save(hotel);
        return hotel;
    }

    public HotelDto getHotel(Long id){
        return hotelRepository.findById(id).map(
                hotel -> new HotelDto(
                        hotel.getHotelName(),
                        hotel.getCity(),
                        hotel.getCountry(),
                        hotel.getStars(),
                        hotel.getNumberOfRooms()
                )).orElseThrow();
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getHotelsFrom(String country) {

        return hotelRepository.findAllByCountry(country);

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

    public List<HotelRoom> getAllHotelRooms() {
        return hotelRoomRepository.findAll();
    }

    public HotelRoom deleteHotelRoom(Long roomId) {
        if(!hotelRoomRepository.existsById(roomId)){
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        HotelRoom hotelRoom = hotelRoomRepository.findById(roomId).orElseThrow(() -> new IllegalStateException(
                        new Exception("Room with provided id does not exist"))
        );
        hotelRoomRepository.deleteById(roomId);
        return hotelRoom;
    }
}
