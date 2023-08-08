package com.sparta.developmentgroup1.post.entity;

import com.sparta.developmentgroup1.boards.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    // @Column 사용에 문제가 생겨서 Entity 붙임

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Post(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public void update(String name) {
        this.name = name;
    }
}