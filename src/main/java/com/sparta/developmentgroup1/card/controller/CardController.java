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
    public ResponseEntity<CardResponseDto> createCard(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody CardRequestDto requestDto) {
        CardResponseDto result = cardService.createCard(requestDto,userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/cards/{id}") //카드 수정
    public ResponseEntity<CardResponseDto> updateCard(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long cardId, @RequestBody CardRequestDto requestDto) {
        try {
            CardResponseDto result = cardService.updateCard(cardId, requestDto, userDetails.getUser()); //삭제할 댓글 아이디와 수정할 내용, 유저
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/cards/{id}") //카드 삭제
    public ResponseEntity<ApiResponseDto> deleteCard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long cardId) {
        try {
            cardService.deleteCard(cardId, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
