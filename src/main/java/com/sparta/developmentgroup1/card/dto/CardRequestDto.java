package com.sparta.developmentgroup1.card.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private Long postId;
    private String title;
    private String description;
    private String background;
    private String developer;
    private String deadline;
}
