package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    Client findByEmail(String email);
}
