package com.sparta.developmentgroup1.post.controller;

import com.sparta.developmentgroup1.post.dto.PostRequestDto;
import com.sparta.developmentgroup1.post.dto.PostUpdateRequestDto;
import com.sparta.developmentgroup1.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
        return ResponseEntity.ok("컬럼 생성 완료");
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<String> updatePost(@RequestBody PostUpdateRequestDto requestDto, @PathVariable Long id) {
        postService.updatePost(requestDto, id);
        return ResponseEntity.ok("컬럼명 수정 완료");
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("컬럼 삭제 완료");
    }
}
