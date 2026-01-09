package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.reservation.Reservation;
import org.hope.booking.domain.reservation.ReservationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public String adminReservationList(Authentication authentication, Model model) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return "redirect:/";
        }

        List<Reservation> allReservations = reservationRepository.findAllByOrderByStartTimeDesc();
        model.addAttribute("reservations", allReservations);
        return "admin/list";
    }

    @PostMapping("/reservations/{id}/cancel")
    public String cancelReservation(@PathVariable Long id, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return "redirect:/";
        }

        reservationRepository.deleteById(id);
        return "redirect:/admin/reservations";
    }
}
