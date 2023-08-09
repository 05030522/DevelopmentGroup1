package com.sparta.developmentgroup1.post.service;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.post.dto.PostRequestDto;
import com.sparta.developmentgroup1.post.dto.PostUpdateRequestDto;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public void createPost(PostRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        Post post = new Post(requestDto.getName(), board);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(PostUpdateRequestDto requestDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));
        post.update(requestDto.getName());
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청한 컬럼이 존재하지 않습니다."));
        postRepository.delete(post);
    }
}