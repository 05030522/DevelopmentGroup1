package com.sparta.developmentgroup1.boards.dto;

public class BoardInviteDto { //보드 초대하는 dto
    private Long boardId; //보드 아이디
    private String[] usernamesToInvite; //사용자 이름 배열로 저장할예쩡

    public Long getBoardId() { //보드 아이디
        return boardId;
    }

    public void setBoardId(Long boardId) { //보드 아이디 설정
        this.boardId = boardId;
    }
    public String[] getUsernamesToInvite() { //사용자 이름 배열
        return usernamesToInvite;
    }

    public void setUsernamesToInvite(String[] usernamesToInvite) { //사용자 이름 배열 설정
        this.usernamesToInvite = usernamesToInvite;
    }
}
