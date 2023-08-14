package com.sparta.developmentgroup1.card.repository;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
//    List<Card> findAllByPostIdOrderByPositionIndexAsc(Long postId);
    List<Card> findAllByPostOrderByPositionIndexAsc(Post post);
}
