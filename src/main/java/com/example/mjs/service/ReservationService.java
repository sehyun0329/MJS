// ReservationService.java
package com.example.mjs.service;

import com.example.mjs.model.Reservation;
import com.example.mjs.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public boolean isReserved(String name, int tableNumber) {
        return reservationRepository.existsByNameAndTableName(name, "reservation" + tableNumber);
    }

    public void reserve(String name, int tableNumber) {
        // Generate the table name
        String tableName = "reservation" + tableNumber;

        // Check if the seat is already reserved
        if (reservationRepository.existsByTableName(tableName)) {
            logger.warn("Seat {} is already reserved", tableNumber);
            throw new IllegalStateException("Already reserved");
        }

        // If not reserved, proceed with the reservation
        Reservation reservation = new Reservation(name, tableName);
        reservationRepository.save(reservation);
        logger.info("Reserved seat {} for {}", tableNumber, name);
    }


    public void returnSeat(String name, int tableNumber) {
        Reservation reservation = reservationRepository.findByNameAndTableName(name, "reservation" + tableNumber);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            logger.info("Returned seat {} for {}", tableNumber, name);
        } else {
            logger.warn("Reservation not found for seat {} and {}", tableNumber, name);
        }
    }
}