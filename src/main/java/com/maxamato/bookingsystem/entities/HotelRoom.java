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
    private int numberOfBeds;
    private Boolean hasPrivateToilet = false;

    private int numberOfClients=0;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "hotel_room_client",
            joinColumns = @JoinColumn(name = "hotel_room_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients = new ArrayList<>();

    public HotelRoom(int numberOfBeds, Boolean hasPrivateToilet, Boolean isAvailable) {
        this.numberOfBeds = numberOfBeds;
        this.hasPrivateToilet=hasPrivateToilet;
        this.isAvailable=isAvailable;
    }

}

