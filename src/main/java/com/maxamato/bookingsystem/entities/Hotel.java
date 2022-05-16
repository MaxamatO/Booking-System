package com.maxamato.bookingsystem.entities;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String hotelName;
    private String city;
    private String country;
    private Integer stars;

    @Column(name = "number_of_rooms", columnDefinition = "integer default 0")
    private Integer numberOfRooms=0;

    @OneToMany(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "hotel_room_id")
    private List<HotelRoom> hotelRooms = new ArrayList<>();

    @Column(name = "is_available_on_summer")
    private Boolean isAvailableOnSummer;


    public Hotel(String hotelName, String city, String country,Integer stars, Boolean isAvailableOnSummer) {
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
        this.isAvailableOnSummer = Objects.requireNonNullElse(isAvailableOnSummer, true);
    }

    public void addHotelRoom(HotelRoom hotelRoom){
        this.hotelRooms.add(hotelRoom);
    }




}
