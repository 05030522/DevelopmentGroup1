package com.sparta.developmentgroup1.card.dto;

import com.sparta.developmentgroup1.card.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardResponseDto extends ApiResponseDto{
    private String title;
    private String description;
    private String background;
    private String developer;

    private LocalDateTime deadline;


    public CardResponseDto(Card card){
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.background = card.getBackground();
        this.developer = card.getDeveloper();
        this.deadline = card.getDeadline();
    }
}
