package com.sparta.developmentgroup1.card.controller;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.dto.DeadlineUpdateDto;
import com.sparta.developmentgroup1.card.dto.MemberRequestDto;
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
    public ResponseEntity<MsgResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto) {
        cardService.updateCard(id, requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("카드 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/cards/{id}") //카드 삭제
    public ResponseEntity<MsgResponseDto> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok().body(new MsgResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/cards/{id}/deadline")
    public ResponseEntity<MsgResponseDto> updateDeadline(@PathVariable Long id,
                                                         @RequestBody DeadlineUpdateDto updateDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.updateDeadline(id, updateDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("마감일 수정 성공", HttpStatus.OK.value()));
    }

    @PostMapping("/cards/{id}/members")
    public ResponseEntity<MsgResponseDto> addMember(@PathVariable Long id,
                                                    @RequestBody MemberRequestDto requestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.addMember(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("작업자 추가 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/cards/{id}/members")
    public ResponseEntity<MsgResponseDto> deleteMember(@PathVariable Long id,
                                                    @RequestBody MemberRequestDto requestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteMember(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("작업자 삭제 성공", HttpStatus.OK.value()));
    }
}
