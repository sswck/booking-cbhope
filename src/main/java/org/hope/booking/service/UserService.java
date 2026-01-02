package org.hope.booking.service;

import lombok.RequiredArgsConstructor;
import org.hope.booking.domain.user.Role;
import org.hope.booking.domain.user.User;
import org.hope.booking.domain.user.UserRepository;
import org.hope.booking.dto.UserSignupDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(UserSignupDto dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = User.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .name(dto.getName())
                .role(Role.USER)    // ADMIN는 DB에서 수동 변경하거나 별도 로직 필요
                .build();

        return userRepository.save(user).getId();
    }
}
