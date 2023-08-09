package com.sparta.developmentgroup1.cardComment.entity;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class CardComment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    //댓글 내용
    @Column(nullable = false)
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//fk
    private User user; //여러개의 댓글을 한 유저가 행할 수 있음. 단방향

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")//fk
    private Card card; //양방향
}
