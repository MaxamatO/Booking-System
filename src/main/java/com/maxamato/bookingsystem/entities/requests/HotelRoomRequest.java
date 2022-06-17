package com.maxamato.bookingsystem.entities.requests;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HotelRoomRequest {

    private int numberOfBeds;
    private Boolean hasPrivateToilet;
    private Boolean isAvailable;
    private Long hotelId;

}

