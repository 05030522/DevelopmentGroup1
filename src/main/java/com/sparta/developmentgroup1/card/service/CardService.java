package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.dto.DeadlineUpdateDto;
import com.sparta.developmentgroup1.card.dto.MemberRequestDto;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.entity.CardUser;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.card.repository.CardUserRepository;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final PostRepository postRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardUserRepository cardUserRepository;

    // 카드 생성
    @Transactional
    public CardResponseDto createCard(CardRequestDto requestDto, Long postId, User user) {
        //칼럼 있는지 확인
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다."));

        // collaborator 제한 추가 예정

        //새로운 카드 만들기
        int lastPosition = post.getCardList().size();
        Card card = new Card(requestDto, post, lastPosition + 1);

        cardRepository.save(card); //DB에 저장
        return new CardResponseDto(card); //CardResponseDto 생성자를 통해 필드 추가
    }

    // 카드 수정
    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
        Card card = findCard(cardId);

        // collaborator 제한 추가 예정

        card.updateCard(requestDto);
        return new CardResponseDto(card);
    }

    // 카드 삭제
    @Transactional
    public void deleteCard(Long cardId) {
        Card card = findCard(cardId);

        // collaborator 제한 추가 예정

        cardRepository.delete(card);
    }

    @Transactional
    public void updateDeadline(Long cardId, DeadlineUpdateDto updateDto, User user) {
        Card card = findCard(cardId);

        // collaborator 제한 추가 예정

        LocalDateTime deadline = updateDto.getDeadline();
        card.updateDeadline(deadline);
    }

    @Transactional
    public void addMember(Long cardId, MemberRequestDto requestDto, User user) {
        Card card = findCard(cardId);
        User member = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다. username을 확인해 주세요.")
        );

        // collaborator 제한 추가 예정

        CardUser cardUser = new CardUser(card, member);
        cardUserRepository.save(cardUser);
    }

    @Transactional
    public void deleteMember(Long cardId, MemberRequestDto requestDto, User user) {
        Card card = findCard(cardId);
        User member = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다. username을 확인해 주세요.")
        );

        // collaborator 제한 추가 예정

        CardUser cardUser = cardUserRepository.findByCardAndMember(card, member);
        cardUserRepository.delete(cardUser);
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new IllegalArgumentException("선택한 카드는 존재하지 않습니다.")
        );
    }

    // 카드 이동(포스트 내에서)

    // 카드 이동(다른 포스트로)
}
