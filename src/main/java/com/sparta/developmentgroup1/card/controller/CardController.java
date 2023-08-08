package com.sparta.developmentgroup1.card.controller;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/cards") //카드 생성
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, User user) {
        CardResponseDto result = cardService.createCard(requestDto,user.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/cards/{id}") //카드 수정
    public ResponseEntity<ApiResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto,  User user) {
        try { // 에러 응답을 위해 ApiResponseDto 사용
            CardResponseDto result = cardService.updateCard(id, requestDto, User.getUser()); //삭제할 댓글 아이디와 수정할 내용, 유저
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/cards/{id}") //카드 삭제
    public ResponseEntity<ApiResponseDto> deleteCard( @PathVariable Long id,  User user) {
        try {
            cardService.deleteCard(id, User.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
