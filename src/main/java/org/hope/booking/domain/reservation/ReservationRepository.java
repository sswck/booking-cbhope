package org.hope.booking.domain.reservation;

import org.hope.booking.domain.facility.Facility;
import org.hope.booking.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.facility = :facility " + "AND r.startTime < :end AND r.endTime > :start")
    List<Reservation> findOverlappingReservations(@Param("facility") Facility facility,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    List<Reservation> findByUserOrderByStartTimeDesc(User user);
}
