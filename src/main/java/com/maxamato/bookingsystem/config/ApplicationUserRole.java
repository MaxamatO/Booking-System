package com.maxamato.bookingsystem.config;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.maxamato.bookingsystem.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(HOTEL_READ, HOTEL_ROOM_READ, BOOKING_READ, BOOKING_WRITE)),
    HOTELOWNER(Sets.newHashSet(HOTEL_READ, HOTEL_WRITE, HOTEL_ROOM_READ, HOTEL_ROOM_WRITE, BOOKING_READ)),
    ADMIN(Sets.newHashSet(HOTEL_READ, HOTEL_WRITE, HOTEL_ROOM_READ, HOTEL_ROOM_WRITE, BOOKING_READ, BOOKING_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;

    }

}
