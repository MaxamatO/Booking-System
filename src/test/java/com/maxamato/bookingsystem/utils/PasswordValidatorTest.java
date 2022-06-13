package com.maxamato.bookingsystem.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTest {

    private PasswordValidator underTest;

    @BeforeEach
    void setUp() {
        underTest = new PasswordValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "1234abc, false",
            "1234abcd1234abcd@#$@$$fsdf#$$$$$, false",
            "withoutupeprcase, false",
            "WithoutSpecialChar, false",
            "WithoutANumber, false",
            "Password123!, true"
    })
    void itShouldValidatePassword(String password, boolean expected){
        boolean isValid = underTest.test(password);
        assertThat(isValid).isEqualTo(expected);
    }
}
