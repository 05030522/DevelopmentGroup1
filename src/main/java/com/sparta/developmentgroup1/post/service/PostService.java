package com.sparta.developmentgroup1.post.service;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.post.dto.PostRequestDto;
import com.sparta.developmentgroup1.post.dto.PostResponseDto;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    // BoardUserRepository 추가 예정

    public List<PostResponseDto> getPost(Long boardId) {
        return postRepository.findAllByBoardIdOrderByPositionAsc(boardId).stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public void createPost(UserDetailsImpl userDetails, Long boardId, PostRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        // 콜라보레이터 조건 추가 예정

        int position = postRepository.findAllByBoardIdOrderByPositionAsc(boardId).size() + 1;

        Post post = new Post(requestDto.getName(), position, board);
        postRepository.save(post);
    }

    @Transactional
    public void updateName(UserDetailsImpl userDetails, PostRequestDto requestDto, Long boardId, Long postId) {
        Post post = postRepository.findByBoardIdAndId(boardId, postId).orElseThrow(
                () -> new IllegalArgumentException("요청한 포스트가 존재하지 않습니다.")
        );

        // 콜라보레이터 조건 추가 예정

        post.update(requestDto.getName());
    }

    @Transactional
    public void deletePost(UserDetailsImpl userDetails, Long boardId, Long postId) {
        Post deletePost = postRepository.findByBoardIdAndId(boardId, postId).orElseThrow(
                () -> new IllegalArgumentException("요청한 포스트가 존재하지 않습니다.")
        );

        // 콜라보레이터 조건 추가 예정

        // 삭제할 포스트의 position 보다 큰 position-1
        List<Post> posts = postRepository.findAllByBoardIdOrderByPositionAsc(boardId);
        for (Post post : posts) {
            if (post.getPosition() > deletePost.getPosition()) {
                post.setPosition(post.getPosition() - 1);
            }
        }

        postRepository.delete(deletePost);
    }

    @Transactional
    public void movePost(UserDetailsImpl userDetails, Long boardId, Long postId, int newPosition) {
        Post post = postRepository.findByBoardIdAndId(boardId, postId).orElseThrow(
                () -> new IllegalArgumentException("요청한 포스트가 존재하지 않습니다.")
        );

        // 콜라보레이터 조건 추가 예정


        int currentPosition = post.getPosition();
        // 새로운 위치가 현재 위치와 다른 경우에만 처리
        if (currentPosition != newPosition) {
            List<Post> posts = postRepository.findAllByBoardIdOrderByPositionAsc(boardId);

        }
    }
}