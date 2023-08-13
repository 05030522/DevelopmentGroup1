package com.sparta.developmentgroup1.card.controller;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.card.service.CardService;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.post.entity.Post;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


    @PostMapping("/cards") //카드 생성
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, Authentication authentication) {
        // Get the currently logged-in user from the authentication object
        User user = (User) authentication.getPrincipal();

        CardResponseDto result = cardService.createCard(requestDto, user);
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
}
