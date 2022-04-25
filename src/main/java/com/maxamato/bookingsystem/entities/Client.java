package com.maxamato.bookingsystem.entities;

import com.maxamato.bookingsystem.dtos.HotelDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "clients_rooms",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_room_id")
    )
    private final List<HotelRoom> bookedRooms = new ArrayList<>();

    public Client(String email, String password, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = Period.between(dateOfBirth, LocalDate.now())
                .getYears()>=18;
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

//    private final List<HotelDto> bookedHotels;

}
