package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.facility.Facility;
import org.hope.booking.domain.facility.FacilityRepository;
import org.hope.booking.domain.reservation.Reservation;
import org.hope.booking.domain.reservation.ReservationRepository;
import org.hope.booking.dto.TimeSlotDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingApiController {
    private final ReservationRepository reservationRepository;
    private final FacilityRepository facilityRepository;

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

    @GetMapping("/available-slots")
    public List<TimeSlotDto> getAvailableSlots(
            @RequestParam("facilityId") Long facilityId,
            @RequestParam("date") String dateStr) {

        LocalDate date = LocalDate.parse(dateStr);
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new IllegalArgumentException("시설이 없습니다."));

        List<TimeSlotDto> slots = new ArrayList<>();

        LocalTime current = LocalTime.of(9, 0);
        LocalTime closeTime = LocalTime.of(22, 0);

        while (current.isBefore(closeTime)) {
            LocalDateTime slotStart = LocalDateTime.of(date, current);
            LocalDateTime slotEnd = slotStart.plusMinutes(30);

            List<Reservation> overlapping = reservationRepository.findOverlappingReservations(facility, slotStart, slotEnd);

            boolean isAvailable = overlapping.isEmpty();
            slots.add(new TimeSlotDto(current.toString(), isAvailable));

            current = current.plusMinutes(30);
        }
        return slots;
    }
}
