package org.hope.booking.config;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.facility.Facility;
import org.hope.booking.domain.facility.FacilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInit {
    private final FacilityRepository facilityRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (facilityRepository.count() == 0) {
                System.out.println("========== 초기 시설물 데이터를 생성합니다 ==========");

                facilityRepository.save(new Facility("상담실 1", "4인용 테이블, 화이트보드 구비"));
                facilityRepository.save(new Facility("상담실 2", "4인용 테이블, 프린터 구비"));
                facilityRepository.save(new Facility("상담실 3", "6인용 테이블, 빔프로젝터 구비"));

                System.out.println("========== 시설물 데이터 생성 완료 (3건) ==========");
            }
        };
    }
}
