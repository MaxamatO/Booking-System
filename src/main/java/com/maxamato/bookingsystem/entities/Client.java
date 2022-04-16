package com.maxamato.bookingsystem.entities;

import com.maxamato.bookingsystem.dtos.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String email;
    private final String password;

    @Column(name = "date_of_birth")
    private final LocalDateTime dateOfBirth;

    private final boolean isAdult;


//    private final List<HotelDto> bookedHotels;

}
