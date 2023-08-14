package com.sparta.developmentgroup1.post.repository;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByBoardIdAndPostId(Long boardId, Long postId);
    List<Post> findAllByBoardIdOrderByPositionAsc(Long boardId);
}
