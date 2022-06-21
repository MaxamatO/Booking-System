package com.maxamato.bookingsystem.config;

public enum ApplicationUserPermission {
    HOTEL_READ("hotel:read"),
    HOTEL_WRITE("hotel:write"),
    HOTEL_ROOM_READ("hotelRoom:read"),
    HOTEL_ROOM_WRITE("hotelRoom:write"),
    BOOKING_READ("booking:read"),
    BOOKING_WRITE("booking:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
