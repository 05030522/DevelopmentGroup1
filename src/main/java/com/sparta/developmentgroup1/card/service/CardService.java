package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.card.dto.CardPositionUpdateRequestDto;
import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.entity.CardPositionInfo;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import com.sparta.developmentgroup1.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final PostRepository postRepository;
    private final CardRepository cardRepository;

    //카드 생성
    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        //칼럼 있는지 확인
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 포스트입니다."));
        int lastPosition = post.getCardList().size();
        //새로운 카드 만들기
        Card card = new Card(requestDto, post, lastPosition + 1);
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
    private void updateCardPosition(CardPositionUpdateRequestDto requestDto){
        for (CardPositionInfo cardPositionInfo : requestDto.getInfoList()) {
            Card card = findCard(cardPositionInfo.getCardId());
            card.setPosition(cardPositionInfo.getPosition());
        } //다른 칼럼으로도 이동 가능하게 구현해야함. 순서는 아무데나
    }
    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }

//    public CardCommentResponseDto createComment(Long cardId) {
//return null;
//    }
}
