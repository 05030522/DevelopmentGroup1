package com.sparta.developmentgroup1.boards.repository;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.entity.BoardUser;
import com.sparta.developmentgroup1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {

    Optional<BoardUser> findByBoardAndCollaborator(Board board, User collaborator);

    Optional<BoardUser> findByBoardIdAndCollaboratorId(Long boardId, Long collaboratorId);
}
