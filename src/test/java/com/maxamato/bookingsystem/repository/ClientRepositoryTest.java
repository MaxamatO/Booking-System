package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Client;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Mock
    private ClientRepository underTest;

    @Test
    void checkIfClientExistsByEmail() {
        // given
        final String email = "test@gmail.com";
        Client client = new Client(
                email,
                "Password12!",
                LocalDate.of(2000, 3, 15),
                "France",
                "23-444",
                "Paris",
                "something",
                44
        );
        underTest.save(client);

        // when
        final boolean b = underTest.existsByEmail(email);

        // then
        assertThat(b).isTrue();
    }

    @Test
    void findByEmail() {
        // given
        final String email = "test@gmail.com";
        Client client = new Client(
                email,
                "Password12!",
                LocalDate.of(2000, 3, 15),
                "France",
                "23-444",
                "Paris",
                "something",
                44
        );
        Client client2 = new Client(
                "test2@gmail.com",
                "Password12!",
                LocalDate.of(2006, 3, 15),
                "France",
                "23-444",
                "Paris",
                "something",
                44
        );
        underTest.save(client);
        underTest.save(client2);
        // when
        Client expected = underTest.findByEmail(email).get();
        // then
        assertThat(expected).isEqualTo(client);


    }
}