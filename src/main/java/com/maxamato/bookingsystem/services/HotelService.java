package com.maxamato.bookingsystem.services;

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

    public String addHotel(HotelRequest hotel){
        hotelRepository.save(new Hotel(
                hotel.getHotelName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getStars(),
                hotel.getIsAvailableOnSummer()

        ));
        return "added";
    }


    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getHotelsFrom(String country) {

        return hotelRepository.findAllByCountry(country);

    }


    public void addHotelRoom(HotelRoomRequest hotelRoomRequest) {

        Hotel hotel = hotelRepository.getById(hotelRoomRequest.getHotelId());

        hotelRoomRepository.save(new HotelRoom(
                hotelRoomRequest.getNumberOfBeds(),
                hotelRoomRequest.getHasPrivateToilet(),
                hotelRoomRequest.getIsAvailable(),
                hotel
        ));

    }

    public List<HotelRoom> getAllHotelRooms() {
        return hotelRoomRepository.findAll();
    }


    public String deleteHotelRoom(Long roomId) {
        if(!hotelRoomRepository.existsById(roomId)){
            throw new IllegalStateException(new Exception("Room with provided id does not exist"));
        }
        hotelRoomRepository.deleteById(roomId);
        return "Deleted";
    }
}
