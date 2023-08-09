package com.sparta.developmentgroup1.boards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateDto { //보드 만드는 dto
    private String name; //보드 이름
    private String backgroundColor; //보드 배경색
    private String description; //보드 설명

}
