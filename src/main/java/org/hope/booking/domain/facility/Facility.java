package org.hope.booking.domain.facility;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 예: 대회의실, 1번 노트북

    private String description;

    private boolean isAvailable;

    public Facility(String name, String description) {
        this.name = name;
        this.description = description;
        this.isAvailable = true;
    }

    // 상태 변경 메서드 (Dirty Checking 활용)
    public void changeAvailability(boolean status) {
        this.isAvailable = status;
    }
}
