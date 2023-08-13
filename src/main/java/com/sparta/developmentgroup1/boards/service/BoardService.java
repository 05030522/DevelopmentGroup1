package com.sparta.developmentgroup1.boards.service;

import com.sparta.developmentgroup1.boards.dto.BoardRequestDto;
import com.sparta.developmentgroup1.boards.dto.BoardResponseDto;
import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.repository.BoardRepository;
import com.sparta.developmentgroup1.boards.repository.BoardUserRepository;
import com.sparta.developmentgroup1.boards.dto.BoardInviteDto;
import com.sparta.developmentgroup1.boards.entity.BoardUser;
import com.sparta.developmentgroup1.common.security.UserDetailsImpl;
import com.sparta.developmentgroup1.user.entity.User;
import com.sparta.developmentgroup1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardUserRepository boardUserRepository;
    private final UserRepository userRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto, User creator) { //보드 생성
        Board board = new Board(requestDto, creator);
        boardRepository.save(board); //보드 저장

        return new BoardResponseDto(board); //보드 생성 결과 반환
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto dto) { //보드 수정
        Board existingBoard = findBoard(id);

        existingBoard.setName(dto.getName()); //보드 이름
        existingBoard.setBackgroundColor(dto.getBackgroundColor()); //보드 배경색
        existingBoard.setDescription(dto.getDescription()); //보드 설명
        boardRepository.save(existingBoard); //보드 저장

        return new BoardResponseDto(existingBoard);
    }

    @Transactional
    public void deleteBoard(Long id, User loginUser) { //보드 삭제
        Board existingBoard = findBoard(id);

        User creator = existingBoard.getCreator();
        if (!loginUser.equals(creator)) {
            throw new RejectedExecutionException("보드를 생성한 사용자만 삭제할 수 있습니다.");
        }
        boardRepository.delete(existingBoard);  //보드 삭제
    }

    @Transactional
    public void inviteUsersToBoard(UserDetailsImpl userDetails, Long id, BoardInviteDto dto) { //보드 초대
        Board board = findBoard(id); //보드 찾기

        User loginUser = userDetails.getUser(); // 로그인한 유저
        User creator = board.getCreator(); // 선택한 보드 생성한 유저

        if (!loginUser.equals(creator)) {
            throw new IllegalArgumentException("보드를 생성한 사용자만 초대할 수 있습니다.");
        }

        User collaborator = userRepository.findByUsername(dto.getUsername()).orElseThrow(
                () ->new IllegalArgumentException("없는 사용자입니다. username을 확인해 주세요."));

        BoardUser boardUser = new BoardUser(board, collaborator);
        boardUserRepository.save(boardUser); //보드 저장
    }

    // 협업유저 삭제 구현 예정

    private Board findBoard(Long id) { //보드 찾기
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("보드를 찾지 못했습니다."));
        return board;
    }
}