package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.reservation.Reservation;
import org.hope.booking.domain.reservation.ReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingApiController {
    private final ReservationRepository reservationRepository;

    @GetMapping
    public List<Map<String, Object>> getAllBookings() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<Map<String, Object>> events = new ArrayList<>();

        for (Reservation r : reservations) {
            Map<String, Object> event = new HashMap<>();
            event.put("id", r.getId());
            event.put("title", "[" + r.getFacility().getName() + "] " + r.getUser().getName());
            event.put("start", r.getStartTime());
            event.put("end", r.getEndTime());

            if (r.getFacility().getName().contains("1")) {
                event.put("color", "#0d6efd");
            } else if (r.getFacility().getName().contains("2")) {
                event.put("color", "#198754");
            } else {
                event.put("color", "#ffc107");
                event.put("textColor", "#000000");
            }

            events.add(event);
        }
        return events;
    }
}
