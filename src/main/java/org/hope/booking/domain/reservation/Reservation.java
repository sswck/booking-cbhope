package org.hope.booking.domain.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hope.booking.domain.user.User;
import org.hope.booking.domain.facility.Facility;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    private String purpose;

    @Builder
    public Reservation(User user, Facility facility, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        // 생성 시점에 유효성 검증 로직을 넣을 수 있습니다.
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }
        this.user = user;
        this.facility = facility;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }
}
