package com.maxamato.bookingsystem.dtos;

public record ClientAddressDto(String email, String country, String city, String street, String postCode, int houseNumber) {
}
