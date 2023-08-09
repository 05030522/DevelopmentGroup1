package com.sparta.developmentgroup1.card.controller;

import com.sparta.developmentgroup1.cardComment.dto.CardCommentResponseDto;
import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.service.CardService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import com.sparta.developmentgroup1.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/cards") //카드 생성
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, User user) {
        CardResponseDto result = cardService.createCard(requestDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/cards/{id}") //카드 수정
    public ResponseEntity<MsgResponseDto> updateCard(@PathVariable Long cardId, @RequestBody CardRequestDto requestDto) {
        cardService.updateCard(cardId, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("카드 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/cards/{id}") //카드 삭제
    public ResponseEntity<MsgResponseDto> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().body(new MsgResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
    }

//    @PostMapping("/cards/{id}/comments") //댓글 생성
//    public ResponseEntity<CardCommentResponseDto> createComment(@PathVariable Long cardId) {
//        CardCommentResponseDto result = cardService.createComment(cardId);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @PutMapping("//cards/{id}/comments/{id}") //댓글 수정
//    public ResponseEntity<MsgResponseDto> updateComment(@PathVariable Long cardId, @RequestBody CardCommentResponseDto requestDto){
//        cardService.updateCard(cardId, requestDto);
//        return ResponseEntity.ok().body(new MsgResponseDto("카드 수정 성공", HttpStatus.OK.value()));
//    }
}