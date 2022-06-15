package com.maxamato.bookingsystem.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    private PasswordValidator underTest;

    @BeforeEach
    void setUp() {
        underTest = new PasswordValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "1234abc, Password must contain 8-20 characters.",
            "1234abcd1234abcd@#$@$$fsdf#$$$$$, Password must contain 8-20 characters.",
            "withoutupeprcase, Password must contain at least one upper case character.",
            "WithoutSpecial5, Password must contain at least one special character.",
            "WithoutANumber!, Password must contain at least one number",
            "WHEREISLOWERCASE, Password can't contain only upper case characters."
    })
    void itShouldValidatePassword_DoesNotPass(String password, String expectedException){
        Exception exception = assertThrows(IllegalStateException.class, () ->
                underTest.test(password));
        String actualException = exception.getMessage();
        assertTrue(actualException.contains(expectedException));
    }

    @Test
    void itShouldValidatePassword_Passes(){
        boolean isValid = underTest.test("Password123!");
        assertThat(isValid).isEqualTo(true);
    }


}
