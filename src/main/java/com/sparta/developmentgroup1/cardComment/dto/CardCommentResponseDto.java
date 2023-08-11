package com.sparta.developmentgroup1.cardComment.dto;

import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import com.sparta.developmentgroup1.cardComment.entity.CardComment;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardCommentResponseDto extends MsgResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String author;


    public CardCommentResponseDto(CardComment comment) {
        this.id = getId();
        this.content = getContent();
        this.createdAt = getCreatedAt();
        this.modifiedAt = getModifiedAt();
        this.author = getAuthor(); // Add the author field to the constructor
    }
}
