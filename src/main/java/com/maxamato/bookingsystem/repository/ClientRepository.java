package com.maxamato.bookingsystem.repository;

import com.maxamato.bookingsystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    boolean existsByEmail(String email);

    @Query(value = "select * from Client c where c.email = ?1", nativeQuery = true)
    Optional<Client> findByEmail(String email);
}
