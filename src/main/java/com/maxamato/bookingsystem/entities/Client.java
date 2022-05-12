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
    private Long clientId;
    private String email;
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private LocalDateTime accountCreatedAt=LocalDateTime.now();
    private boolean isAdult;
    private String postCode;
    private String street;
    private String country;
    private String city;
    private int houseNumber;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "clients_rooms",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_room_id")
    )
    private List<HotelRoom> bookedRooms = new ArrayList<>();

    public Client(String email, String password, LocalDate dateOfBirth, String postCode,String street,String country,String city, int houseNumber) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears()>=18;
        this.postCode = postCode;
        this.street = street;
        this.country = country;
        this.city = city;
        this.houseNumber = houseNumber;
    }
    public Client(String email, String password, LocalDate dateOfBirth, HotelRoom hotelRoom ) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears()>=18;
        bookedRooms.add(hotelRoom);
    }

    private Boolean isAdult(int years){
        return years >= 18;
    }
}
