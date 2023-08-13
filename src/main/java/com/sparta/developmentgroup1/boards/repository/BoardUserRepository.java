package com.sparta.developmentgroup1.boards.repository;

import com.sparta.developmentgroup1.boards.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
    Optional<BoardUser> findByBoardIdAndCollaboratorId(Long boardId, Long userId);
    List<BoardUser> findAllByBoardId(Long boardId);
}
