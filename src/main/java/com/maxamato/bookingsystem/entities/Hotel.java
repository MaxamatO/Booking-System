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
    private Long hotelId;

    private String hotelName;
    private String city;
    private String country;
    private Integer stars;
    private Integer numberOfRooms;


    @Column(name = "is_available_on_summer", columnDefinition = "boolean default true")
    private Boolean isAvailableOnSummer=true;

    public Hotel(String hotelName, String city, String country,int stars) {
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.stars = stars;
    }
}
