package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import com.maxamato.bookingsystem.services.ClientService;
import com.maxamato.bookingsystem.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/booking_system")
public class HotelController {

    private final HotelService hotelService;
    // ****** HOTEL PART *******

    @PostMapping(path = "hotel/add")
    public Hotel addHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.addHotel(hotelRequest);
    }

    @GetMapping(path = "hotels/alls")
    public List<HotelDto> getHotels(){
        return hotelService.getHotels().stream().map(hotelRequest -> new HotelDto(hotelRequest.getHotelName(), hotelRequest.getCity(),
                hotelRequest.getCountry(), hotelRequest.getStars(), hotelRequest.getNumberOfRooms())).collect(Collectors.toList());
    }

    @GetMapping(path = "hotels/all")
    public List<Hotel> getHotelss(){
        return hotelService.getHotels();
    }

    @GetMapping(path = "hotels/from/")
    public List<HotelDto> getHotelsFrom(@RequestParam(name = "country") String country){
        return hotelService.getHotelsFrom(country).stream().map(hotelRequest -> new HotelDto(hotelRequest.getHotelName(), hotelRequest.getCity(),
                hotelRequest.getCountry(), hotelRequest.getStars(), hotelRequest.getNumberOfRooms())).collect(Collectors.toList());
    }

    @GetMapping(path = "hotels/")
    public HotelDto getHotel(@RequestParam(name = "id") Long id){
        return hotelService.getHotel(id);
    }

    // ****** HOTEL ROOM PART *******

    @PostMapping("hotel/room/add")
    public HotelRoom addHotelRoom(@RequestBody HotelRoomRequest hotelRoomRequest){
        return hotelService.addHotelRoom(hotelRoomRequest);

    }

    @GetMapping("hotel/rooms/all")
    public List<HotelRoom> getAllHotelRooms(){
        return hotelService.getAllHotelRooms();
    }

    @DeleteMapping("hotel/room/{roomId}/delete")
    public HotelRoom deleteHotelRoom(@PathVariable Long roomId){
        return hotelService.deleteHotelRoom(roomId);
    }

}
