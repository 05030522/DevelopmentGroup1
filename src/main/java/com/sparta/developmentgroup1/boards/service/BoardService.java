package com.sparta.developmentgroup1.boards.service;

import com.sparta.developmentgroup1.boards.dto.BoardCreateDto;
import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.dto.BoardResponseDto;
import com.sparta.developmentgroup1.boards.dto.BoardUpdateDto;
import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Autowired
    public BoardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public BoardResponseDto createBoard(BoardCreateDto dto, User creator) { //보드 생성
        Board board = new Board();
        board.setName(dto.getName()); //보드 이름
        board.setBackgroundColor(dto.getBackgroundColor());  //보드 배경색
        board.setDescription(dto.getDescription()); //보드 설명
        board.setCreator(creator); //보드 생성자

        Board savedBoard = boardRepository.save(board); //보드 저장

        return new BoardResponseDto(savedBoard); //보드 생성 결과 반환
    }

    public Board updateBoard(Long id, BoardUpdateDto dto) { //보드 수정
        Board existingBoard = boardRepository.findById(id) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다.")); //보드가 없으면 에러
        existingBoard.setName(dto.getName()); //보드 이름
        existingBoard.setBackgroundColor(dto.getBackgroundColor()); //보드 배경색
        existingBoard.setDescription(dto.getDescription()); //보드 설명
        return boardRepository.save(existingBoard); //보드 저장
    }

    public void deleteBoard(Long id) { //보드 삭제
        Board existingBoard = boardRepository.findById(id) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다.")); //보드가 없으면 에러
        boardRepository.delete(existingBoard);  //보드 삭제
    }

    public void inviteUsersToBoard(BoardInviteDto dto) { //보드 초대
        Board board = boardRepository.findById(dto.getBoardId()) //보드 찾기
                .orElseThrow(() -> new RuntimeException("보드를 찾을 수 없습니다.")); //보드가 없으면 에러

        Set<User> collaborators = new HashSet<>(); //초대할 사용자들
        for (String username : dto.getUsernamesToInvite()) { //초대할 사용자들을 반복문으로 돌면서
            Optional<User> userOptional = userRepository.findByUsername(username); //사용자 찾기
            if (userOptional.isPresent()) { //사용자가 있으면
                User user = userOptional.get(); //사용자 정보 가져오기
                collaborators.add(user); //초대할 사용자들에 추가
            }
        }
        board.getCollaborators().addAll(collaborators); //보드에 초대할 사용자들 추가
        boardRepository.save(board); //보드 저장
    }
}