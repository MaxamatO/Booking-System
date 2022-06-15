package com.maxamato.bookingsystem.utils;

import java.util.function.Predicate;

public class PasswordValidator implements Predicate<String> {

    @Override
    public boolean test(String password) {
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialSymbols = "(.*[@$%^&*()_#!].*)";
        String numbers = "(.*[0-9].*)";

        if (password.length() < 8 || password.length() > 20) {
            throw new IllegalStateException(new Exception("Password must contain 8-20 characters."));

        }

        if (!password.matches(upperCaseChars)) {
            throw new IllegalStateException(new Exception("Password must contain at least one upper case character."));
        }

        if (!password.matches(lowerCaseChars)) {
            throw new IllegalStateException(new Exception("Password can't contain only upper case characters."));
        }

        if (!password.matches(specialSymbols)) {
            throw new IllegalStateException(new Exception("Password must contain at least one special character."));
        }

        if (!password.matches(numbers)) {
            throw new IllegalStateException(new Exception("Password must contain at least one number"));
        }
        return true;
    }

}
