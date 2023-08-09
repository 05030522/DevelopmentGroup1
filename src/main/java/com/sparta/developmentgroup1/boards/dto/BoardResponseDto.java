package com.sparta.developmentgroup1.boards.dto;

import com.sparta.developmentgroup1.boards.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto { // 보드 응답 dto
    private Long id;
    private String name;
    private String backgroundColor;
    private String description;

    public BoardResponseDto(Board savedBoard) { // 보드 생성자
        this.id = savedBoard.getId();
        this.name = savedBoard.getName();
        this.backgroundColor = savedBoard.getBackgroundColor();
        this.description = savedBoard.getDescription();
    }
}