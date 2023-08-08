package com.sparta.developmentgroup1.boards.dto;

public class BoardInviteDto { //보드 초대하는 dto
    private Long boardId;
    private String[] usernamesToInvite; //사용자 이름 배열로 저장할예쩡

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
