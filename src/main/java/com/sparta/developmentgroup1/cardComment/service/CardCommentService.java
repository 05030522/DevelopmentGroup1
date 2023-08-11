package com.sparta.developmentgroup1.cardComment.service;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.cardComment.dto.CardCommentRequestDto;
import com.sparta.developmentgroup1.cardComment.dto.CardCommentResponseDto;
import com.sparta.developmentgroup1.cardComment.entity.CardComment;
import com.sparta.developmentgroup1.cardComment.repository.CardCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardCommentService {
    private final CardRepository cardRepository;
    private final CardCommentRepository cardCommentRepository;
    // 댓글 생성
    public CardCommentResponseDto createComment(Long cardId, CardCommentRequestDto requestDto) {
        Card card = findCard(cardId);
        CardComment comment = new CardComment(requestDto, card);

        comment.setCard(card); //조회한 카드 받고
        comment.setContent(comment.getContent());
        comment.setAuthor(comment.getAuthor());
         var savedCardComment = cardCommentRepository.save(comment); //DB에 저장
        return new CardCommentResponseDto(savedCardComment); //CardCommentResponseDto 생성자를 통해 필드 추가
    }
    @Transactional //댓글 수정
    public void updateComment(Long id, CardCommentResponseDto requestDto) {
        CardComment comment = findComment(id); // 댓글 찾기

        comment.setContent(requestDto.getContent());

        cardCommentRepository.save(comment); // 변경된 카드 저장
    }
    @Transactional
    public void deleteComment(Long id, CardCommentResponseDto requestDto) {
        CardComment comment = findComment(id); // 댓글 찾기

        comment.setContent(requestDto.getContent());

        cardCommentRepository.delete(comment); // 변경된 카드 저장
    }
    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }
    private CardComment findComment(Long commentId) {
        return cardCommentRepository.findById(commentId).orElseThrow(() ->
         new IllegalArgumentException("선택한 댓글은 존재하지 않습니다."));
    }
}
