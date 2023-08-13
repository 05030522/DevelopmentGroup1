package com.sparta.developmentgroup1.comment.dto;

import com.sparta.developmentgroup1.comment.entity.Comment;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends MsgResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String author;


    public CommentResponseDto(Comment comment) {
        this.id = getId();
        this.content = getContent();
        this.createdAt = getCreatedAt();
        this.modifiedAt = getModifiedAt();
        this.author = getAuthor(); // Add the author field to the constructor
    }
}
