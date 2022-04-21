package com.maxamato.bookingsystem.entities.requests;

import com.maxamato.bookingsystem.entities.Hotel;
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
    private final Integer stars;
    private Boolean isAvailableOnSummer;

}
