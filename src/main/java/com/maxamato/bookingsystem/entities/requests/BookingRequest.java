package com.maxamato.bookingsystem.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BookingRequest {

    private final Long clientId;
    private final Long roomId;
    private final LocalDate accommodationDate;
    private final LocalDate evictionDate;
}
