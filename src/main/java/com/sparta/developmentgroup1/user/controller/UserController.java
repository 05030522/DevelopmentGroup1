package com.sparta.developmentgroup1.user.controller;

import com.sparta.developmentgroup1.common.dto.ApiResponseDto;
import com.sparta.developmentgroup1.common.jwt.JwtUtil;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.user.dto.LoginRequestDto;
import com.sparta.developmentgroup1.user.dto.SignupRequestDto;
import com.sparta.developmentgroup1.user.dto.UserInfoDto;
import com.sparta.developmentgroup1.user.entity.UserRoleEnum;
import com.sparta.developmentgroup1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : fieldErrors) {
                return ResponseEntity.badRequest().body(
                        new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage())
                );
            }
        }
        userService.signup(requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "회원 가입 성공"));
    }

    @PostMapping("/login") // 로그인 처리를 위한 URL 추가
    public ResponseEntity<ApiResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 입력값에 오류가 있는 경우, 오류 메시지를 응답으로 전송
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                return ResponseEntity.badRequest().body(
                        new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), fieldError.getField() + " 필드: " + fieldError.getDefaultMessage())
                );
            }
        }
        try {
            // 주입된 AuthenticationManager를 사용하여 인증 시도
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    requestDto.getUsername(),
                    requestDto.getPassword()
            );
            authentication = authenticationManager.authenticate(authentication);

            // 인증 성공시 토큰 생성
            String token = jwtUtil.createToken(requestDto.getUsername(), UserRoleEnum.USER);

            // 토큰을 응답으로 전송
            HttpHeaders headers = new HttpHeaders();
            headers.add(JwtUtil.AUTHORIZATION_HEADER, token);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new ApiResponseDto(HttpStatus.OK.value(), "로그인 성공"));
        } catch (AuthenticationException e) {
            // 인증 실패시 오류 메시지를 응답으로 전송
            return ResponseEntity.badRequest().body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "로그인 실패"));
        }
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