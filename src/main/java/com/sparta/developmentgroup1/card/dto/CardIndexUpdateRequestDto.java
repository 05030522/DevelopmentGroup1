package com.sparta.developmentgroup1.card.dto;

import com.sparta.developmentgroup1.card.entity.CardIndexInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class CardIndexUpdateRequestDto {
    private List<CardIndexInfo> infoList;
}
