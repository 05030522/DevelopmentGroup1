package com.sparta.developmentgroup1.boards.dto;

import com.sparta.developmentgroup1.boards.entity.Area;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AreaRequestDto {
    private Long boardId;
    private String name;

    @Builder
    public Area toEntity() {
        return Area.builder()
                .name(this.name)
                .build();
    }
}
