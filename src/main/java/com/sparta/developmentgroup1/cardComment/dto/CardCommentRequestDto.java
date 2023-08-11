package com.sparta.developmentgroup1.cardComment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCommentRequestDto {
    private String content;
    private Long cardId;
}
