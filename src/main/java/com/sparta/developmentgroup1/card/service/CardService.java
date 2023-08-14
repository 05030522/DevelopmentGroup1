package com.sparta.developmentgroup1.card.service;

import com.sparta.developmentgroup1.card.dto.*;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.entity.CardUser;
import com.sparta.developmentgroup1.card.repository.CardRepository;
import com.sparta.developmentgroup1.card.repository.CardUserRepository;
import com.sparta.developmentgroup1.post.dto.PostResponseDto;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.post.repository.PostRepository;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto, User user) {
        Card card = findCard(cardId);

        // collaborator 제한 추가 예정

        card.updateCard(requestDto);
        return new CardResponseDto(card);
    }

    // 카드 삭제
    @Transactional
    public void deleteCard(Long cardId, User user) {
        Card card = findCard(cardId);
        Post post = card.getPost();

        // collaborator 제한 추가 예정

        // 삭제할 카드의 position 보다 큰 position-1
        List<Card> cards = cardRepository.findAllByPostOrderByPositionIndexAsc(post);
        for (Card c : cards) {
            if (c.getPositionIndex() > card.getPositionIndex()) {
                c.movePositionIndex(c.getPositionIndex() - 1);
            }
        }
        cardRepository.delete(card);
    }

    // 포스트 내 위치 변경
    @Transactional
    public List<CardResponseDto> movePosition(Long cardId, PositionRequestDto requestDto, User user) {
        Card card = findCard(cardId);
        Post post = card.getPost();
        int newPosition = requestDto.getPositionIndex();
        int currentPosition = card.getPositionIndex();

        // collaborator 제한 추가 예정

        // 새로운 위치가 현재 위치와 다른 경우에만 처리
        if (currentPosition != newPosition) {
            List<Card> cards = cardRepository.findAllByPostOrderByPositionIndexAsc(post);

            // 새 위치가 유효한 범위 내에 있는지 확인
            newPosition = Math.min(Math.max(newPosition, 1), cards.size() + 1);
            // 이동 방향에 따라 다른 카드 위치 조정
            if (currentPosition < newPosition) {
                for (Card c : cards) {
                    if (c.getPositionIndex() > currentPosition && c.getPositionIndex() <= newPosition) {
                        c.movePositionIndex(c.getPositionIndex() - 1);
                    }
                }
            } else {
                for (Card c : cards) {
                    if (c.getPositionIndex() >= newPosition && c.getPositionIndex() < currentPosition) {
                        c.movePositionIndex(c.getPositionIndex() + 1);
                    }
                }
            }
            // 카드의 새 위치 설정
            card.movePositionIndex(newPosition);
        }
        return cardRepository.findAllByPostOrderByPositionIndexAsc(post).stream().map(CardResponseDto::new)
                .collect(Collectors.toList());
    }

    // 카드 이동(다른 포스트로)
    @Transactional
    public List<CardResponseDto> movePost(Long cardId, MovePostRequestDto requestDto, User user) {
        Card card = findCard(cardId);
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );

        // collaborator 제한 추가 예정

        card.movePost(post);
        return cardRepository.findAllByPostOrderByPositionIndexAsc(post).stream().map(CardResponseDto::new)
                .collect(Collectors.toList());
    }

    // 마감일 수정
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
}
