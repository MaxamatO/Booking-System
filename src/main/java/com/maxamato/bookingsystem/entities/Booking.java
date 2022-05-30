package com.maxamato.bookingsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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


    public Booking(Client client, HotelRoom hotelRoom) {
        this.client = client;
        this.hotelRoom = hotelRoom;
    }
}
