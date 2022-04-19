package com.maxamato.bookingsystem.entities.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClientRequest {

    private final String email;
    private final String password;
    private final LocalDate dateOfBirth;

}
