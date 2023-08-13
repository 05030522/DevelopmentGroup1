package com.sparta.developmentgroup1.comment.controller;

import com.sparta.developmentgroup1.comment.dto.CommentRequestDto;
import com.sparta.developmentgroup1.comment.dto.CommentResponseDto;
import com.sparta.developmentgroup1.comment.service.CommentService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/cards/{cardId}/comments") //댓글 생성
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long cardId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto result = commentService.createComment(cardId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comments/{commentId}") //댓글 수정
    public ResponseEntity<MsgResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentResponseDto requestDto){
        commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<MsgResponseDto> deleteComment(@PathVariable Long commentId, @RequestBody CommentResponseDto requestDto){
        commentService.deleteComment(commentId, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}
