package com.sparta.developmentgroup1.post.controller;

import com.sparta.developmentgroup1.common.dto.ApiResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.post.dto.PostMoveRequestDto;
import com.sparta.developmentgroup1.post.dto.PostRequestDto;
import com.sparta.developmentgroup1.post.dto.PostResponseDto;
import com.sparta.developmentgroup1.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/{boardId}/posts") // 포스트 조회
    public ResponseEntity<List<PostResponseDto>> getPost(@PathVariable Long boardId) {
        List<PostResponseDto> postList = postService.getPost(boardId);
        return ResponseEntity.ok().body(postList);
    }

    @PostMapping("/{boardId}/posts") // 포스트 생성
    public ResponseEntity<ApiResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long boardId,
                                                     @RequestBody PostRequestDto requestDto) {
        postService.createPost(userDetails.getUser(), boardId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto(HttpStatus.CREATED.value(), "포스트 생성 완료"));
    }

    @PatchMapping("/{boardId}/posts/{postId}") // 포스트명 수정
    public ResponseEntity<ApiResponseDto> updateName(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody PostRequestDto requestDto,
                                                     @PathVariable Long boardId,
                                                     @PathVariable Long postId) {
        postService.updateName(userDetails.getUser(), requestDto, boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "포스트 수정 완료"));
    }

    @DeleteMapping("/{boardId}/posts/{postId}") // 포스트 삭제
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long boardId,
                                                     @PathVariable Long postId) {
        postService.deletePost(userDetails.getUser(), boardId, postId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "포스트 삭제 완료"));
    }

    @PatchMapping("/{boardId}/posts/{postId}/move") // 포스트 순서 이동
    public ResponseEntity<List<PostResponseDto>> movePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @PathVariable Long boardId,
                                                    @PathVariable Long postId,
                                                    @RequestBody PostMoveRequestDto requestDto) {
        List<PostResponseDto> postList = postService.movePost(userDetails.getUser(), boardId, postId, requestDto);
        return ResponseEntity.ok().body(postList);
    }
}
