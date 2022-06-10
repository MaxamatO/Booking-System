package com.maxamato.bookingsystem.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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

    public Client(String email, String password, LocalDate dateOfBirth,
                  String postCode, String street, String country,
                  String city, int houseNumber) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.city = city;
        this.houseNumber = houseNumber;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears() >= 18;
    }
    
}
