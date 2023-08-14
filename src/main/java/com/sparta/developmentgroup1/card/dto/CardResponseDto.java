package com.sparta.developmentgroup1.card.dto;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.common.dto.MsgResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardResponseDto extends MsgResponseDto {
    private String postName;
    private String title;
    private String description;
    private String background;
    private int positionIndex;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deadline;


    public CardResponseDto(Card card){
        this.postName = card.getPost().getName();
        this.positionIndex = card.getPositionIndex();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.background = card.getBackground();
        this.createdAt = card.getCreatedAt();
        this.modifiedAt = card.getModifiedAt();
        this.deadline = card.getDeadline();
    }
}
