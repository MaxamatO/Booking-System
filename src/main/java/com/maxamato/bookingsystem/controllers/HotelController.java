package com.maxamato.bookingsystem.controllers;

import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.Hotel;
import com.maxamato.bookingsystem.entities.HotelRoom;
import com.maxamato.bookingsystem.entities.requests.ClientRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
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
    public String addHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.addHotel(hotelRequest);
    }

    @GetMapping(path = "hotels/all")
    public List<HotelDto> getHotels(){
        return hotelService.getHotels().stream().map(hotelRequest -> new HotelDto(hotelRequest.getHotelName(), hotelRequest.getCity(),
                hotelRequest.getCountry(), hotelRequest.getStars())).collect(Collectors.toList());
    }

    @GetMapping(path = "hotels/")
    public List<HotelDto> getHotelsFrom(@RequestParam(name = "country") String country){
        return hotelService.getHotelsFrom(country).stream().map(hotelRequest -> new HotelDto(hotelRequest.getHotelName(), hotelRequest.getCity(),
                hotelRequest.getCountry(), hotelRequest.getStars())).collect(Collectors.toList());
    }

    // ****** HOTEL ROOM PART *******

    @PostMapping("hotel/room/add")
    public String addHotelRoom(@RequestBody HotelRoomRequest hotelRoomRequest){
        hotelService.addHotelRoom(hotelRoomRequest);
        return "added";
    }

    @GetMapping("hotel/rooms/all")
    public List<HotelRoom> getAllHotelRooms(){
        return hotelService.getAllHotelRooms();
    }

    // ********* Client Part ********


}
