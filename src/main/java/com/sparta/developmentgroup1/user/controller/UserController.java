package com.sparta.developmentgroup1.user.controller;


import com.sparta.developmentgroup1.user.dto.LoginRequestDto;
import com.sparta.developmentgroup1.user.dto.SignupRequestDto;
import com.sparta.developmentgroup1.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("user/sigup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("user/sigup")
    public String signup(SignupRequestDto requestDto) {
        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            return  "redirect:/api/user/login-page?error";
        }

        return "redirect:/";
    }
}
