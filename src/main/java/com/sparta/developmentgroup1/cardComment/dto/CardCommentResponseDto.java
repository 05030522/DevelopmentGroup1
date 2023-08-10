package com.sparta.developmentgroup1.cardComment.dto;

import com.sparta.developmentgroup1.card.dto.CardResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardCommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String author;

    public CardCommentResponseDto(Long id, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String author) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.author = author; // Add the author field to the constructor
    }
}
