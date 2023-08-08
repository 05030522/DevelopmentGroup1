package com.sparta.developmentgroup1.post.repository;

import com.sparta.developmentgroup1.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
