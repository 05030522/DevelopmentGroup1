package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.card.dto.CardPositionInfo;
import com.sparta.developmentgroup1.card.dto.CardPositionUpdateRequestDto;
import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.cardComment.dto.CardCommentResponseDto;
import com.sparta.developmentgroup1.cardComment.entity.CardComment;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CardService {

    private final PostRepository postRepository;
    private final CardRepository cardRepository;

    // 카드 생성
    @Transactional
    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        // 조회한 포스트 정보 가져오기
        Post post = findPost(requestDto.getPostId());

        // 새로운 카드 생성
        int lastPosition = post.getCardList().size();
        Card card = new Card(requestDto, post, lastPosition + 1);
        card.setUser(user);
        card.setPost(post);
        var savedCard = cardRepository.save(card);
        return new CardResponseDto(savedCard);
    }

    // 카드 수정
    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
        Card card = findCard(cardId);
        card.update(requestDto);
        return new CardResponseDto(card.update(requestDto));
    }

    // 카드 삭제
    @Transactional
    public void deleteCard(Long cardId) {
        Card card = findCard(cardId);
        cardRepository.delete(card);
    }

    // 카드 이동(칼럼 내에서)
    private void updateCardPosition(CardPositionUpdateRequestDto requestDto) {
        for (CardPositionInfo cardPositionInfo : requestDto.getInfoList()) {
            Card card = findCard(cardPositionInfo.getCardId());
            card.setPosition(cardPositionInfo.getPosition());
        }
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }


    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 컬럼입니다."));
    }


    //카드 생성
    public CardResponseDto createCard(CardRequestDto requestDto) {
        //칼럼 있는지 확인
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 포스트입니다."));
        int lastPosition = post.getCardList().size();
        //새로운 카드 만들기
        Card card = new Card(requestDto, post, lastPosition + 1);
        card.setPost(post); //조회한 칼럼 받고
        var savedCard = cardRepository.save(card); //DB에 저장
        return new CardResponseDto(savedCard); //CardResponseDto 생성자를 통해 필드 추가
    }


    @Transactional
    public void updateComment(Long cardId, Long commentId, CardCommentResponseDto requestDto) {
        Card card = findCard(cardId);
        CardComment comment = findComment(card, commentId); // 댓글 찾기

        comment.setContent(requestDto.getContent());

        cardRepository.save(card); // 변경된 카드 저장
    }

    private CardComment findComment(Card card, Long commentId) {
        for (CardComment comment : card.getComments()) {
            if (comment.getId().equals(commentId)) {
                return comment;
            }
        }
        throw new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.");
    }

    @Transactional
    public CardCommentResponseDto createComment(Long cardId, CardCommentResponseDto requestDto) {
        Card card = findCard(cardId);

        CardComment comment = new CardComment();
        comment.setContent(requestDto.getContent());
        comment.setAuthor(requestDto.getAuthor());

        card.getComments().add(comment);
        cardRepository.save(card);

        CardCommentResponseDto createdCommentDto = new CardCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getAuthor()
        );

        return createdCommentDto;
    }
}
