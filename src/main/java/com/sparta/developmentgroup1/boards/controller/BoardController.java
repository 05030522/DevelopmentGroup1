package com.sparta.developmentgroup1.boards.controller;

import com.sparta.developmentgroup1.boards.dto.BoardCreateDto;
import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.dto.BoardUpdateDto;
import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards") //일단 미정이지만 대충 적어둠
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public Board createBoard(@RequestBody BoardCreateDto dto) { //보드 생성
        return boardService.createBoard(dto);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody BoardUpdateDto dto) { //보드 수정
        return boardService.updateBoard(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) { //보드 삭제
        boardService.deleteBoard(id);
    }

    @PostMapping("/{id}/invite")
    public void inviteUsers(@PathVariable Long id, @RequestBody BoardInviteDto dto) { //보드 초대
        dto.setBoardId(id);
        boardService.inviteUsersToBoard(dto);
    }
}
