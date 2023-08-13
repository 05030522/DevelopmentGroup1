package com.sparta.developmentgroup1.boards.repository;

import com.sparta.developmentgroup1.boards.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
}
