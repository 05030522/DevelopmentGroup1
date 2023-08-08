package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PostService postService;
    private final CardRepository cardRepository;

    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        //칼럼 있는지 확인
        Post post = postService.findPost(requestDto.getColumnId());
        //새로운 카드 만들기
        Card card = new Card(requestDto, post);
        card.setUser(user); //유저 정보 받고
        card.setPost(post); //조회한 칼럼 받고
        var savedCard = cardRepository.save(card); //DB에 저장
        return new CardResponseDto(savedCard); //CardResponseDto 생성자를 통해 필드 추가
    }
}
