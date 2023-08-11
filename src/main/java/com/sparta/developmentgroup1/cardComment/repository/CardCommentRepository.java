package com.sparta.developmentgroup1.cardComment.repository;

import com.sparta.developmentgroup1.cardComment.entity.CardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardCommentRepository extends JpaRepository<CardComment, Long> {
}
