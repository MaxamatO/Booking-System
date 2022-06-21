package com.maxamato.bookingsystem.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.maxamato.bookingsystem.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(HOTEL_READ, HOTEL_ROOM_READ, BOOKING_READ, BOOKING_WRITE)),
    ADMIN(Sets.newHashSet(HOTEL_READ, HOTEL_WRITE, HOTEL_ROOM_READ, HOTEL_ROOM_WRITE, BOOKING_READ, BOOKING_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
