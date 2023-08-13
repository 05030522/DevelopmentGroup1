package com.sparta.developmentgroup1.boards.controller;

import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.dto.BoardRequestDto;
import com.sparta.developmentgroup1.boards.dto.BoardResponseDto;
import com.sparta.developmentgroup1.boards.service.BoardService;
import com.sparta.developmentgroup1.common.dto.ApiResponseDto;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService; //보드 서비스

    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto dto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) { //보드 생성
        BoardResponseDto result = boardService.createBoard(dto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result); //보드 생성 결과 반환
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto dto) { //보드 수정
        BoardResponseDto result = boardService.updateBoard(id, dto); //보드 수정 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<ApiResponseDto> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.deleteBoard(id, userDetails.getUser()); //보드 삭제
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "보드 삭제 완료"));
    }

    @PostMapping("/boards/{id}/invitations")
    public ResponseEntity<ApiResponseDto> inviteUsers(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                      @PathVariable Long id, @RequestBody BoardInviteDto dto) {
        boardService.inviteUsersToBoard(userDetails, id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto(HttpStatus.OK.value(), "사용자 초대 완료"));
    }

    @DeleteMapping("/boards/{boardId}/invitations/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUserFromBoard(@PathVariable Long boardId, @PathVariable Long userId) {
        boardService.deletedUserFromBoard(boardId, userId);
        ApiResponseDto response = new ApiResponseDto(HttpStatus.OK.value(), "사용자 삭제 완료");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
