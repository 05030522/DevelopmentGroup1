package com.sparta.developmentgroup1.post.service;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.entity.BoardUser;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.boards.repository.BoardUserRepository;
import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.service.CardService;
import com.sparta.developmentgroup1.post.dto.PostMoveRequestDto;
import com.sparta.developmentgroup1.post.dto.PostRequestDto;
import com.sparta.developmentgroup1.post.dto.PostResponseDto;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import com.sparta.developmentgroup1.user.entity.User;
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
    private final BoardUserRepository boardUserRepository;
    private final CardService cardService;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPost(Long boardId) {
        return postRepository.findAllByBoardIdOrderByPositionAsc(boardId).stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createPost(User loginUser, Long boardId, PostRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        verifyCollaborator(boardId, loginUser); // 협업 초대되어있는 유저인지 확인

        int position = postRepository.findAllByBoardIdOrderByPositionAsc(boardId).size() + 1;

        Post post = new Post(requestDto.getName(), position, board);
        postRepository.save(post);
    }

    @Transactional
    public void createPostWithCard(User loginUser, Long boardId, PostRequestDto requestDto, CardRequestDto cardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("요청한 보드가 존재하지 않습니다.")
        );

        verifyCollaborator(boardId, loginUser); // 협업 초대되어있는 유저인지 확인

        int position = postRepository.findAllByBoardIdOrderByPositionAsc(boardId).size() + 1;

        Post post = new Post(requestDto.getName(), position, board);
        postRepository.save(post);

        // 카드 생성
        CardRequestDto cardDto = new CardRequestDto();
        cardDto.setTitle(cardRequestDto.getTitle());
        cardDto.setDescription(cardRequestDto.getDescription());
        cardDto.setBackground(cardRequestDto.getBackground());
        cardDto.setDeadline(cardRequestDto.getDeadline());

        cardService.createCard(cardDto, loginUser);
    }

    @Transactional
    public void updateName(User loginUser, PostRequestDto requestDto, Long boardId, Long postId) {
        Post post = findPost(boardId, postId);

        verifyCollaborator(boardId, loginUser); // 협업 초대되어있는 유저인지 확인

        post.updateName(requestDto.getName());
    }

    @Transactional
    public void deletePost(User loginUser, Long boardId, Long postId) {
        Post deletePost = findPost(boardId, postId);

        verifyCollaborator(boardId, loginUser); // 협업 초대되어있는 유저인지 확인

        // 삭제할 포스트의 position 보다 큰 position-1
        List<Post> posts = postRepository.findAllByBoardIdOrderByPositionAsc(boardId);
        for (Post post : posts) {
            if (post.getPosition() > deletePost.getPosition()) {
                post.movePosition(post.getPosition() - 1);
            }
        }

        postRepository.delete(deletePost);
    }

    @Transactional
    public List<PostResponseDto> movePost(User loginUser, Long boardId, Long postId, PostMoveRequestDto requestDto) {
        Post post = findPost(boardId, postId);

        verifyCollaborator(boardId, loginUser); // 협업 초대되어있는 유저인지 확인

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
                        p.movePosition(p.getPosition() - 1);
                    }
                }
            } else {
                for (Post p : posts) {
                    if (p.getPosition() >= newPosition && p.getPosition() < currentPosition) {
                        p.movePosition(p.getPosition() + 1);
                    }
                }
            }
            // 이동된 포스트의 새 위치 설정
            post.movePosition(newPosition);
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

    private void verifyCollaborator(Long boardId, User loginUser) {
        List<BoardUser> collaborators = boardUserRepository.findAllByBoardId(boardId);
        boolean isCollaborator = false;
        for (BoardUser collaborator : collaborators) {
            if (collaborator.getCollaborator().getId().equals(loginUser.getId())) {
                isCollaborator = true;
                break;
            }
        }
        if (!isCollaborator) {
            throw new IllegalArgumentException("협업 등록된 사용자만 생성할 수 있습니다.");
        }
    }
}