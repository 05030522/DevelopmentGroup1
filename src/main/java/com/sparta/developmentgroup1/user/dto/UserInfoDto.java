package com.sparta.developmentgroup1.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String nickname;
    String username;
    boolean isAdmin;

    public UserInfoDto(String nickname){
        this.nickname = nickname;
    }
}