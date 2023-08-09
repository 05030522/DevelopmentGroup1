package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.cardComment.dto.CardCommentResponseDto;
import com.sparta.developmentgroup1.card.dto.CardIndexInfo;
import com.sparta.developmentgroup1.card.dto.CardIndexUpdateRequestDto;
import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.entity.CardIndexInfo;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.service.PostService;
import com.sparta.developmentgroup1.post.repository.PostRepository

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final PostRepository postRepository;
    private final PostService postService;

    //카드 생성
    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        //칼럼 있는지 확인

        Post post = postRepository.findPost(requestDto.getColumnId());
        int lastindex = post.getCardList.size();

        //새로운 카드 만들기
        Card card = new Card(requestDto, post);
      
        card.setUser(user); //유저 정보 받고
        card.setPost(post); //조회한 칼럼 받고
        var savedCard = cardRepository.save(card); //DB에 저장
        return new CardResponseDto(savedCard); //CardResponseDto 생성자를 통해 필드 추가
    }

    //카드 수정
    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
        //DB에 존재하는지 확인
        Card card = findCard(cardId);
        card.update(requestDto);
        // 내용 수정
        return new CardResponseDto(card.update(requestDto));
    }

    //카드 삭제
    @Transactional
    public void deleteCard(Long cardId) {
        //  DB에 존재하는지 확인
        Card card = findCard(cardId);
        cardRepository.delete(card);
    }
    //카드 이동(칼럼 내에서)
    private void updateCardIndex(CardIndexUpdateRequestDto requestDto){
        for (CardIndexInfo cardIndexInfo : requestDto.getInfoList()) {
            Card card = findCard(cardIndexInfo.getCardId());
            card.setIndex(cardIndexInfo.getIndex());
        } //다른 칼럼으로도 이동 가능하게 구현해야함. 순서는 아무데나
    }
    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }
    private Columns findColumn(Long columnId) {
        return columnRepository.findById(columnId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 컬럼입니다."));
    }

    public CardCommentResponseDto createComment(Long cardId) {

    }
}
