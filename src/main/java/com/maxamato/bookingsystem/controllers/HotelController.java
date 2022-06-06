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
@RequestMapping(path = "api/v1/booking_system")
public class HotelController {

    private final HotelService hotelService;
    // ****** HOTEL PART *******

    @PostMapping(path = "hotel/add")
    public HotelDto addHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.addHotel(hotelRequest);
    }

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

    @DeleteMapping(path = "hotel/{hotel_id}/delete")
    public String deleteHotel(@PathVariable(name = "hotel_id") Long id){
        return hotelService.deleteHotel(id);
    }

    // ****** HOTEL ROOM PART *******

    @PostMapping("hotel/room/add")
    public HotelRoomDto addHotelRoom(@RequestBody HotelRoomRequest hotelRoomRequest){
        return hotelService.addHotelRoom(hotelRoomRequest);

    }

    @GetMapping("hotel/room/{roomId}")
    public HotelRoomDto getHotelRoomById(@PathVariable Long roomId){
        return hotelService.getHotelRoomById(roomId);
    }

    @GetMapping("hotel/rooms/all")
    public List<HotelRoomDto> getAllHotelRooms(){
        return hotelService.getAllHotelRooms();
    }

    @DeleteMapping("hotel/room/{roomId}/delete")
    public String deleteHotelRoom(@PathVariable Long roomId){
        return hotelService.deleteHotelRoom(roomId);
    }

}
