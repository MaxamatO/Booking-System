package com.maxamato.bookingsystem.entities.requests;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ClientRequest {

    private final String email;
    private final String password;
    private final LocalDate dateOfBirth;
    private final String postCode;
    private final String street;
    private final String country;
    private final String city;
    private final int houseNumber;

}
