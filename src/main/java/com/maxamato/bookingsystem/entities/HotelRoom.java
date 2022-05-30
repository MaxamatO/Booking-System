package com.maxamato.bookingsystem.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isAvailable = true;
    private Integer numberOfBeds;
    private Boolean hasPrivateToilet = false;
    private Integer numberOfClients = 0;

    public HotelRoom(int numberOfBeds, Boolean hasPrivateToilet, Boolean isAvailable) {
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet = hasPrivateToilet;
        this.isAvailable = isAvailable;
    }
}

