package com.sparta.developmentgroup1.post.entity;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.card.entity.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String name;

    @Column
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Card> cardList = new ArrayList<>();

    public void setPosition(int position) {
        this.position = position;
    }

    public Post(String name, int position , Board board) {
        this.name = name;
        this.board = board;

    }

    public void update(String name) {
        this.name = name;
    }

}
