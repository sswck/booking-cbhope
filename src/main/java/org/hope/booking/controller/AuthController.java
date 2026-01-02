package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.dto.UserSignupDto;
import org.hope.booking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(UserSignupDto dto) {
        userService.signup(dto);
        return "redirect:/auth/login";
    }
}
