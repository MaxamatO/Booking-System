package com.maxamato.bookingsystem.entities.requests;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HotelRoomRequest {

    private int numberOfBeds;
    private final Boolean hasPrivateToilet=false;
    private final Boolean isAvailable=true;
    private Long hotelId;

}

