package com.maxamato.bookingsystem.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String email;
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private LocalDateTime accountCreatedAt = LocalDateTime.now();
    private boolean isAdult;
    private String postCode;
    private String street;
    private String country;
    private String city;
    private int houseNumber;

    public Client(String email, String password, LocalDate dateOfBirth, String postCode, String street, String country, String city, int houseNumber) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears() >= 18;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.city = city;
        this.houseNumber = houseNumber;
    }

    public Client(String email, String password, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears() >= 18;
    }

//    public Client(Booking booking){
//        bookings.add(booking);
//    }

    private Boolean isAdult(int years) {
        return years >= 18;
    }
}
