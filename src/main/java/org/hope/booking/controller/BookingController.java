package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.reservation.Reservation;
import org.hope.booking.dto.BookingFormDto;
import org.hope.booking.service.FacilityService;
import org.hope.booking.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final FacilityService facilityService;
    private final ReservationService reservationService;

    @GetMapping("/new")
    public String createBookingForm(Model model) {
        model.addAttribute("facilities", facilityService.findFacilities());

        return "booking/booking-form";
    }

    @PostMapping("/new")
    public String createBooking(BookingFormDto dto, Principal principal, Model model) {
        try {
            reservationService.makeReservation(dto, principal.getName());

            return "redirect:/";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("facilities", facilityService.findFacilities());

            return "booking/booking-form";
        }
    }

    @GetMapping("/my-list")
    public String myReservations(Principal principal, Model model) {
        List<Reservation> reservations = reservationService.findMyReservations(principal.getName());
        model.addAttribute("reservations", reservations);

        return "booking/my-list";
    }

    @GetMapping("/status")
    public String statusPage() {
        return "booking/status";
    }
}
