package com.maxamato.bookingsystem.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {

    // TODO: Client 1:M Booking M:1 HotelRoom, restructure all the code

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
