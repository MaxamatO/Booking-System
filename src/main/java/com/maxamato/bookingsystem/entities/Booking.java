package com.maxamato.bookingsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private HotelRoom hotelRoom;

    private LocalDate bookedAt = LocalDate.now();
    private LocalDate accommodationDate;
    private LocalDate evictionDate;
    private Integer totalAmountOfNights;


    public Booking(Client client, HotelRoom hotelRoom, LocalDate accommodationDate, LocalDate evictionDate) {
        this.client = client;
        this.hotelRoom = hotelRoom;
        this.accommodationDate = accommodationDate;
        this.evictionDate = evictionDate;
        this.totalAmountOfNights = Period.between(accommodationDate, evictionDate).getDays();
    }
}
