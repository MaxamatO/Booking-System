package com.maxamato.bookingsystem.entities.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HotelRequest {

    private final String hotelName;
    private final String city;
    private final String country;
    private final int stars;

}
