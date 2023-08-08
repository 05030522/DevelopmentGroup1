package com.sparta.developmentgroup1.boards.service;

import com.sparta.developmentgroup1.boards.dto.BoardCreateDto;
import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.dto.BoardUpdateDto;
import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

/*    @Autowired
    private UserRepository userRepository;*/

    public Board createBoard(BoardCreateDto dto) { //보드 생성
        Board board = new Board();
/*        board.setName(dto.getName()); //보드 이름
        board.setBackgroundColor(dto.getBackgroundColor()); //보드 배경색
        board.setDescription(dto.getDescription()); //보드 설명

        board.setCreator(userRepository.findByUsername("exampleUsername")); //보드 생성자*/
        return boardRepository.save(board); //보드 저장
    }

    public Board updateBoard(Long id, BoardUpdateDto dto) { //보드 수정
        Board existingBoard = boardRepository.findById(id) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다.")); //보드가 없으면 에러
     /*   existingBoard.setName(dto.getName()); //보드 이름
        existingBoard.setBackgroundColor(dto.getBackgroundColor()); //보드 배경색
        existingBoard.setDescription(dto.getDescription()); //보드 설명*/
        return boardRepository.save(existingBoard); //보드 저장
    }

    public void deleteBoard(Long id) { //보드 삭제
        Board existingBoard = boardRepository.findById(id) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다.")); //보드가 없으면 에러
        boardRepository.delete(existingBoard);  //보드 삭제
    }

    public void inviteUsersToBoard(BoardInviteDto dto) { //보드 초대
        Board board = boardRepository.findById(dto.getBoardId()) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다.")); //보드가 없으면 에러

/*        Set<User> collaborators = new HashSet<>(); //보드에 초대할 사용자들
        for (String username : dto.getUsernamesToInvite()) { //보드에 초대할 사용자들의 이름
            User user = userRepository.findByUsername(username); //사용자 찾기
            if (user != null) { //사용자가 있으면
                collaborators.add(user); //사용자 추가
            }
        }
        board.getCollaborators().addAll(collaborators); //보드에 초대할 사용자들 추가*/
        boardRepository.save(board); //보드 저장
    }
}