package com.sparta.developmentgroup1.comment.repository;

import com.sparta.developmentgroup1.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
