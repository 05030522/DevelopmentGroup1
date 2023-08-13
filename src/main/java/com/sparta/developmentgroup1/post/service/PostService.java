package com.sparta.developmentgroup1.post.service;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.post.dto.PostMoveRequestDto;
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
    // 협업 권한 설정을 위한 BoardUserRepository 추가 예정

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
        Post post = findPost(boardId, postId);

        // 콜라보레이터 조건 추가 예정

        post.update(requestDto.getName());
    }

    @Transactional
    public void deletePost(UserDetailsImpl userDetails, Long boardId, Long postId) {
        Post deletePost = findPost(boardId, postId);

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
    public List<PostResponseDto> movePost(UserDetailsImpl userDetails, Long boardId, Long postId, PostMoveRequestDto requestDto) {
        Post post = findPost(boardId, postId);

        // 콜라보레이터 조건 추가 예정

        int newPosition = requestDto.getPosition();
        int currentPosition = post.getPosition();

        // 새로운 위치가 현재 위치와 다른 경우에만 처리
        if (currentPosition != newPosition) {
            List<Post> posts = postRepository.findAllByBoardIdOrderByPositionAsc(boardId);

            // 재배치를 위해 목록에서 해당 포스트 제거
            posts.remove(post);
            // 새 위치가 유효한 범위 내에 있는지 확인
            newPosition = Math.min(Math.max(newPosition, 1), posts.size() + 1);

            // 이동 방향에 따라 다른 포스트 위치 조정
            if (currentPosition < newPosition) {
                for (Post p : posts) {
                    if (p.getPosition() > currentPosition && p.getPosition() <= newPosition) {
                        p.setPosition(p.getPosition() - 1);
                    }
                }
            } else {
                for (Post p : posts) {
                    if (p.getPosition() >= newPosition && p.getPosition() < currentPosition) {
                        p.setPosition(p.getPosition() + 1);
                    }
                }
            }
            // 이동된 포스트의 새 위치 설정
            post.setPosition(newPosition);
        }
        return postRepository.findAllByBoardIdOrderByPositionAsc(boardId).stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    private Post findPost(Long boardId, Long postId) {
        Post post = postRepository.findByBoardIdAndPostId(boardId, postId).orElseThrow(
                () -> new IllegalArgumentException("요청한 포스트가 존재하지 않습니다.")
        );
        return post;
    }
}