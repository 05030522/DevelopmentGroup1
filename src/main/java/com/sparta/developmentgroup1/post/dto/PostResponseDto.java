package com.sparta.developmentgroup1.post.dto;

import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long boardId;
    private Long postId;
    private String name;
    private int position;
    private List<CardResponseDto> cardList;

    public PostResponseDto(Post post) {
        this.boardId = post.getBoard().getId();
        this.postId = post.getPostId();
        this.name = post.getName();
        this.position = post.getPosition();
        this.cardList = post.getCardList().stream()
                .map(CardResponseDto::new)
                .sorted(Comparator.comparing(CardResponseDto::getPositionIndex))
                .toList();
    }
}