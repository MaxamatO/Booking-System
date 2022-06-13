package com.maxamato.bookingsystem.utils;

import java.util.function.Predicate;

public class PasswordValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return (s.length()>8 && s.length() < 20)
                && s.matches("(.*[A-Z].*)")
                && s.matches("(.*[a-z].*)")
                && s.matches("(.*[@$%^&*()_#!].*)")
                && s.matches("(.*[0-9].*)");
    }

}
