package com.sparta.developmentgroup1.card.controller;

import com.sparta.developmentgroup1.card.dto.*;
import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.service.CardService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/{postId}/cards") //카드 생성
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto,
                                                      @PathVariable Long postId,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto result = cardService.createCard(requestDto, postId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/cards/{id}") //카드 수정
    public ResponseEntity<MsgResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateCard(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("카드 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/cards/{id}") //카드 삭제
    public ResponseEntity<MsgResponseDto> deleteCard(@PathVariable Long id,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(id, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
    }


    @PostMapping("/cards/{id}/members") // 작업자 할당
    public ResponseEntity<MsgResponseDto> addMember(@PathVariable Long id,
                                                    @RequestBody MemberRequestDto requestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.addMember(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("작업자 추가 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/cards/{id}/members") // 작업자 삭제
    public ResponseEntity<MsgResponseDto> deleteMember(@PathVariable Long id,
                                                       @RequestBody MemberRequestDto requestDto,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteMember(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("작업자 삭제 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/cards/{id}/move-position") // 카드 순서 이동
    public ResponseEntity<List<CardResponseDto>> movePosition(@PathVariable Long id,
                                                              @RequestBody PositionRequestDto requestDto,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CardResponseDto> cardList = cardService.movePosition(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(cardList);
    }

    @PatchMapping("/cards/{id}/move-post") // 카드 다른 포스트로 이동
    public ResponseEntity<List<CardResponseDto>> movePost(@PathVariable Long id,
                                                          @RequestBody MovePostRequestDto requestDto,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CardResponseDto> cardList = cardService.movePost(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(cardList);
    }

    @PatchMapping("/cards/{id}/deadline") // 마감일 수정
    public ResponseEntity<MsgResponseDto> updateDeadline(@PathVariable Long id,
                                                         @RequestBody DeadlineUpdateDto updateDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateDeadline(id, updateDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("마감일 수정 성공", HttpStatus.OK.value()));
    }
}
