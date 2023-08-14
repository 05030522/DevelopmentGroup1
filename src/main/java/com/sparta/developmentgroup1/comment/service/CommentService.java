package com.sparta.developmentgroup1.comment.service;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.comment.dto.CommentRequestDto;
import com.sparta.developmentgroup1.comment.dto.CommentResponseDto;
import com.sparta.developmentgroup1.comment.entity.Comment;
import com.sparta.developmentgroup1.comment.repository.CommentRepository;
import com.sparta.developmentgroup1.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CardRepository cardRepository;
    private final CommentRepository commentRepository;

    // 댓글 생성
    public CommentResponseDto createComment(Long cardId, CommentRequestDto requestDto, User user) {
        Card card = findCard(cardId);
        Comment comment = new Comment(requestDto, card, user);

         commentRepository.save(comment); //DB에 저장
        return new CommentResponseDto(comment); //CardCommentResponseDto 생성자를 통해 필드 추가
    }

    @Transactional //댓글 수정
    public void updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(id); // 댓글 찾기

        if (!comment.getCreator().equals(user)) {
            throw new IllegalArgumentException("작성자 본인만 수정할 수 있습니다.");
        }

        comment.updateContent(requestDto.getContent());

        commentRepository.save(comment); // 변경된 카드 저장
    }
    @Transactional
    public void deleteComment(Long id, User user) {
        Comment comment = findComment(id); // 댓글 찾기

        if (!comment.getCreator().equals(user)) {
            throw new IllegalArgumentException("작성자 본인만 수정할 수 있습니다.");
        }

        commentRepository.delete(comment); // 변경된 카드 저장
    }
    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }
    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
         new IllegalArgumentException("선택한 댓글은 존재하지 않습니다."));
    }
}
