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

}
