package com.sparta.developmentgroup1.cardComment.controller;

import com.sparta.developmentgroup1.cardComment.dto.CardCommentRequestDto;
import com.sparta.developmentgroup1.cardComment.dto.CardCommentResponseDto;
import com.sparta.developmentgroup1.cardComment.service.CardCommentService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardCommentController {
    private final CardCommentService cardCommentService;

    @PostMapping("/cards/{cardId}/comments") //댓글 생성
    public ResponseEntity<CardCommentResponseDto> createComment(@PathVariable Long cardId, @RequestBody CardCommentRequestDto requestDto) {
        CardCommentResponseDto result = cardCommentService.createComment(cardId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comments/{commentId}") //댓글 수정
    public ResponseEntity<MsgResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CardCommentResponseDto requestDto){
        cardCommentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<MsgResponseDto> deleteComment(@PathVariable Long commentId, @RequestBody CardCommentResponseDto requestDto){
        cardCommentService.deleteComment(commentId, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}
