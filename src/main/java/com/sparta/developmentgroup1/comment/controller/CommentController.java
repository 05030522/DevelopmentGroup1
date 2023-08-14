package com.sparta.developmentgroup1.comment.controller;

import com.sparta.developmentgroup1.comment.dto.CommentRequestDto;
import com.sparta.developmentgroup1.comment.dto.CommentResponseDto;
import com.sparta.developmentgroup1.comment.service.CommentService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/cards/{cardId}/comments") //댓글 생성
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long cardId, @RequestBody CommentRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto result = commentService.createComment(cardId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/comments/{commentId}") //댓글 수정
    public ResponseEntity<MsgResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.updateComment(commentId, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<MsgResponseDto> deleteComment(@PathVariable Long commentId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
    }
}
