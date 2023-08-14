package com.sparta.developmentgroup1.comment.entity;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.comment.dto.CommentRequestDto;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")//fk
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;


    public Comment(CommentRequestDto requestDto, Card card, User creator) {
        this.content = requestDto.getContent();
        this.card = card;
        this.creator = creator;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
