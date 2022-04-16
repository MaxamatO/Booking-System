package com.maxamato.bookingsystem.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelRoomId;

    private Boolean isAvailable = true;
    private int numberOfBeds;
    private Boolean hasPrivateToilet = false;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @NotNull
    private Hotel hotel;


    private int numberOfClients=0;

    public HotelRoom(int numberOfBeds, Boolean hasPrivateToilet, Boolean isAvailable, Hotel hotel) {
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet=hasPrivateToilet;
        this.isAvailable=isAvailable;
        this.hotel = hotel;
    }
}

