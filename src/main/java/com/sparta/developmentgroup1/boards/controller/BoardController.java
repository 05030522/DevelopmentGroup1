package com.sparta.developmentgroup1.boards.controller;

import com.sparta.developmentgroup1.boards.dto.BoardCreateDto;
import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.dto.BoardResponseDto;
import com.sparta.developmentgroup1.boards.dto.BoardUpdateDto;
import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.service.BoardService;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards") //일단 미정이지만 대충 적어둠
public class BoardController {

    @Autowired
    private BoardService boardService; //보드 서비스
    @Autowired
    private UserService userService; //유저 서비스

    @PostMapping("/create")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardCreateDto requestDto) { //보드 생성
        User creator = userService.findUserByUsername("exampleUsername"); // 사용자 정보 가져오기
        BoardResponseDto result = boardService.createBoard(requestDto, creator);
        return ResponseEntity.status(HttpStatus.CREATED).body(result); //보드 생성 결과 반환
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardUpdateDto dto) { //보드 수정
        return boardService.updateBoard(id, dto); //보드 수정 결과 반환
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id); //보드 삭제
    }

    @PostMapping("/{id}/invite")
    public void inviteUsers(@PathVariable Long id, @RequestBody BoardInviteDto dto) { //보드 초대
        dto.setBoardId(id); //보드 아이디 설정
        boardService.inviteUsersToBoard(dto);
    }
}
