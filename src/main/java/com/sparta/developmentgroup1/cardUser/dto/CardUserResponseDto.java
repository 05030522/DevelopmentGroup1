package com.sparta.developmentgroup1.cardUser.dto;

import com.sparta.developmentgroup1.cardUser.entity.CardUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUserResponseDto {
    private Long id;
    private String username;

    public CardUserResponseDto(CardUser cardUser) {
        this.id = cardUser.getId();
        this.username = cardUser.getUser().getUsername();
    }
}
