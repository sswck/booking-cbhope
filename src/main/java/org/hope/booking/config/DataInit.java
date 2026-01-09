package org.hope.booking.config;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.facility.Facility;
import org.hope.booking.domain.facility.FacilityRepository;
import org.hope.booking.domain.user.Role;
import org.hope.booking.domain.user.User;
import org.hope.booking.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInit {
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (facilityRepository.count() == 0) {
                System.out.println("========== 초기 시설물 데이터를 생성합니다 ==========");

                facilityRepository.save(new Facility("상담실 3", "사용가능"));

                System.out.println("========== 시설물 생성 완료 ==========");
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                System.out.println("========== 관리자 계정(admin)을 생성합니다 ==========");
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234")) // 비밀번호: 1234
                        .name("관리자")
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
            }
        };
    }
}
