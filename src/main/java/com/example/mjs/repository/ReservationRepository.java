package com.example.mjs.repository;

import com.example.mjs.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByNameAndTableName(String name, String tableName);
    Reservation findByName(String name);
    Reservation findByNameAndTableName(String name, String tableName);

    boolean existsByName(String name);

    boolean existsByTableName(String tableName);
}
