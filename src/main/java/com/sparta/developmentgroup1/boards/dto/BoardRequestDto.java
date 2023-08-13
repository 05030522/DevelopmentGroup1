package com.sparta.developmentgroup1.boards.dto;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardRequestDto {
    @NotBlank
    private String name;

    private String backgroundColor;
    private String description;

    public Board toEntity(User creator) {
        return Board.builder()
                .name(this.name)
                .backgroundColor(this.backgroundColor)
                .description(this.description)
                .creator(creator)
                .build();
    }
}