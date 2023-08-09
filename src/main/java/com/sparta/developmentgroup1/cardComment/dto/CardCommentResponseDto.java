package com.sparta.developmentgroup1.cardComment.dto;

import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CardCommentResponseDto {
    private List<CardResponseDto> cardsList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public CardCommentResponseDto(List<CardResponseDto> cardList) {
        this.cardsList = cardList;
    }
}
