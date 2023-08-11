package com.sparta.developmentgroup1.user.controller;

import com.sparta.developmentgroup1.common.dto.ApiResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.user.dto.SignupRequestDto;
import com.sparta.developmentgroup1.user.dto.UserInfoDto;
import com.sparta.developmentgroup1.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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