package com.sparta.developmentgroup1.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private Long cardId;
}
