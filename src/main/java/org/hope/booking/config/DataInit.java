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

                facilityRepository.save(new Facility("상담실 3", "상담실 3입니다."));

                System.out.println("========== 시설물 생성 완료 ==========");
            }
        };
    }
}
