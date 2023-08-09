package com.sparta.developmentgroup1.card.dto;

import com.sparta.developmentgroup1.card.entity.CardPositionInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class CardPositionUpdateRequestDto {
    private List<CardPositionInfo> infoList;
}
