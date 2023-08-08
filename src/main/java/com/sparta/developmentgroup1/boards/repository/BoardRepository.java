package com.sparta.developmentgroup1.boards.repository;

import com.sparta.developmentgroup1.boards.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  BoardRepository extends JpaRepository<Board, Long> { //보드 레포지토리
}
