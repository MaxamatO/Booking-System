package com.maxamato.bookingsystem.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "hotels")
public class Hotel {
    public enum Stars{
        ONE, TWO, THREE, FOUR, FIVE
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long hotelId;

    private String hotelName;
    private String city;
    private String country;
    private Stars stars;
    private Integer numberOfRooms;


    @Column(name = "is_available_on_summer", columnDefinition = "boolean default true")
    private Boolean isAvailableOnSummer=true;

    public Hotel(String hotelName, String city, String country,Stars stars) {
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
    }
}
