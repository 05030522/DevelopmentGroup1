package com.sparta.developmentgroup1.post.dto;

import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long boardId;
    private String name;
    private List<CardResponseDto> cardList;

    public PostResponseDto(Post post) {
        this.boardId = post.getBoard().getId();
        this.name = post.getName();
//        this.cardList =
    }
}