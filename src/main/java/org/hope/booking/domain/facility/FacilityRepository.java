package org.hope.booking.domain.facility;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility,Long> {
    // 필요한 경우 시설 이름으로 찾는 메서드 등을 추가할 수 있습니다.
}
