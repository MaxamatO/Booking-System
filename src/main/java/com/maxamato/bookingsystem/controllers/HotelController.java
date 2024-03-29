package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import com.maxamato.bookingsystem.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "view/api/v1/booking_system")
public class HotelController {

    private final HotelService hotelService;
    // ****** HOTEL PART *******


    @GetMapping(path = "hotels/all")
    public List<HotelDto> getHotels(){
        return hotelService.getHotels();
    }

    @GetMapping(path = "hotels/from/")
    public List<HotelDto> getHotelsFrom(@RequestParam(name = "country") String country){
        return hotelService.getHotelsFrom(country);
    }

    @GetMapping(path = "hotels/")
    public HotelDto getHotel(@RequestParam(name = "id") Long id){
        return hotelService.getHotel(id);
    }


    // ****** HOTEL ROOM PART *******



    @GetMapping("hotel/room/{roomId}")
    public HotelRoomDto getHotelRoomById(@PathVariable Long roomId){
        return hotelService.getHotelRoomById(roomId);
    }

    @GetMapping("hotel/rooms/all")
    public List<HotelRoomDto> getAllHotelRooms(){
        return hotelService.getAllHotelRooms();
    }



}
