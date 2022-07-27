package com.maxamato.bookingsystem.controllers;


import com.maxamato.bookingsystem.dtos.HotelDto;
import com.maxamato.bookingsystem.dtos.HotelRoomDto;
import com.maxamato.bookingsystem.entities.requests.HotelRequest;
import com.maxamato.bookingsystem.entities.requests.HotelRoomRequest;
import com.maxamato.bookingsystem.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("management/api/v1/booking_system")
public class HotelManagementController {

    private final HotelService hotelService;

    // ************************* HOTEL PART *************************

    @PostMapping(path = "hotel/add")
    @PreAuthorize("hasAuthority('hotel:write')")
    public HotelDto addHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.addHotel(hotelRequest);
    }

    @DeleteMapping(path = "hotel/{hotel_id}/delete")
    @PreAuthorize("hasAnyRole('ROLE_HOTELOWNER', 'ROLE_ADMIN')")
    public String deleteHotel(@PathVariable(name = "hotel_id") Long id){
        return hotelService.deleteHotel(id);
    }

    // ************************* HOTEL ROOM PART *************************

    @PostMapping("hotel/room/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOTELOWNER')")
    public HotelRoomDto addHotelRoom(@RequestBody HotelRoomRequest hotelRoomRequest){
        return hotelService.addHotelRoom(hotelRoomRequest);
    }

    @DeleteMapping("hotel/room/{roomId}/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'HOTELOWNER')")
    public String deleteHotelRoom(@PathVariable Long roomId){
        return hotelService.deleteHotelRoom(roomId);
    }

}
