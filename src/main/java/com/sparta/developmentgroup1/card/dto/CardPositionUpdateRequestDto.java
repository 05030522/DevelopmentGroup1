package com.sparta.developmentgroup1.card.dto;


import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class CardPositionUpdateRequestDto {
    private List<CardPositionInfo> infoList;
}

