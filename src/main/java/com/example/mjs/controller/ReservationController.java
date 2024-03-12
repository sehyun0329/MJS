// ReservationController.java
package com.example.mjs.controller;

import com.example.mjs.model.Reservation;
import com.example.mjs.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String index(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservationPage/Reservation";
    }

    @PostMapping("/reserve")
    public String reserve(@RequestParam String name, @RequestParam int tableNumber, Model model) {
        boolean isReservationSuccessful = false;
        String reservationError = null;

        try {
            if (reservationService.isReserved(name, tableNumber)) {
                reservationError = "Seat " + tableNumber + " is already reserved.";
            } else {
                reservationService.reserve(name, tableNumber);
                isReservationSuccessful = true;
            }
        } catch (IllegalStateException e) {
            reservationError = e.getMessage();
        }

        // Reload reservations after the operation
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);

        // Add a flag indicating whether the reservation was successful
        model.addAttribute("isReservationSuccessful", isReservationSuccessful);
        // Add the specific error message
        model.addAttribute("reservationError", reservationError);

        return "reservationPage/Reservation";
    }


    @PostMapping("/return")
    public String returnSeat(@RequestParam String name, @RequestParam int tableNumber, Model model) {
        reservationService.returnSeat(name, tableNumber);
        model.addAttribute("message", "Returned seat " + tableNumber + " for " + name);

        // Reload reservations after the operation
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservationPage/Reservation";

    }

    @GetMapping("/ReservationList")
    public String reservationList(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservationPage/ReservationList";
    }

}
