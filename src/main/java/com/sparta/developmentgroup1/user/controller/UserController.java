package com.sparta.developmentgroup1.user.controller;

import com.sparta.developmentgroup1.common.dto.ApiResponseDto;
import com.sparta.developmentgroup1.common.jwt.JwtUtil;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.user.dto.LoginRequestDto;
import com.sparta.developmentgroup1.user.dto.SignupRequestDto;
import com.sparta.developmentgroup1.user.dto.UserInfoDto;
import com.sparta.developmentgroup1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) throws IllegalAccessException {
        userService.signup(requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "회원 가입 성공"));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        return null;
    }

    @GetMapping("/user-info")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserInfoDto userInfoDto = userService.getUserInfo(userDetails.getUser());
        return userInfoDto;
    }

    @PutMapping("/{userId}/nickname")
    public ResponseEntity<ApiResponseDto> updateNickname(@PathVariable String userId, @RequestBody String newNickname) {
        try {
            userService.updateNickname(userId, newNickname);
            return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "닉네임 업데이트 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "사용자를 찾을 수 없습니다."));
        }
    }
}