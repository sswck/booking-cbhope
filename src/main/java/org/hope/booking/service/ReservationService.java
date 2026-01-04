package org.hope.booking.service;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.facility.Facility;
import org.hope.booking.domain.facility.FacilityRepository;
import org.hope.booking.domain.reservation.Reservation;
import org.hope.booking.domain.reservation.ReservationRepository;
import org.hope.booking.domain.user.User;
import org.hope.booking.domain.user.UserRepository;
import org.hope.booking.dto.BookingFormDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;

    public Long makeReservation(BookingFormDto dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Facility facility = facilityRepository.findById(dto.getFacilityId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시설입니다."));

        LocalDateTime start = dto.getStartDateTime();
        LocalDateTime end = dto.getEndDateTime();

        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalStateException("종료 시간은 시작 시간보다 이후여야 합니다.");
        }

        List<Reservation> overlapping = reservationRepository.findOverlappingReservations(facility, start, end);
        if (!overlapping.isEmpty()) {
            throw new IllegalStateException("해당 시간에 이미 예약이 존재합니다.");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .facility(facility)
                .startTime(start)
                .endTime(end)
                .purpose(dto.getPurpose())
                .build();

        return reservationRepository.save(reservation).getId();
    }

    public List<Reservation> findMyReservations(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return reservationRepository.findByUserOrderByStartTimeDesc(user);
    }
}
