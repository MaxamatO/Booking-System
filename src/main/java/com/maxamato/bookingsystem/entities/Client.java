package com.maxamato.bookingsystem.entities;

import com.maxamato.bookingsystem.dtos.HotelDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
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

    private final boolean isAdult= Period.between(dateOfBirth, LocalDate.now())
            .getYears()>=18;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "clients_rooms",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_room_id")
    )
    private final Set<HotelRoom> bookedRooms = new HashSet<>();

    public Client(String email, String password, LocalDate dateOfBirth) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    private Boolean isAdult(int years){
        return years >= 18;
    }

//    private final List<HotelDto> bookedHotels;

}
