package com.sparta.developmentgroup1.card.entity;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.comment.entity.Comment;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import com.sparta.developmentgroup1.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cards")
@NoArgsConstructor
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String background;

    @Column
    private LocalDateTime deadline;

    @Column(nullable = false)
    private int positionIndex;

    @ManyToOne
    @JoinColumn(name = "post_id") // fk
    private Post post;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();


    public Card(CardRequestDto requestDto, Post post, int positionIndex) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.background = requestDto.getBackground();
        this.deadline = requestDto.getDeadline();
        this.positionIndex = positionIndex; // 생성자에서 받은 값으로 설정
        this.post = post;
    }

    public Card update(CardRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getDescription() != null) {
            this.description = requestDto.getDescription();
        }
        if (requestDto.getBackground() != null) {
            this.background = requestDto.getBackground();
        }
        if (requestDto.getDeadline() != null) {
            this.deadline = requestDto.getDeadline();
        }
        return this;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setPosition(int position) {
        this.positionIndex = position;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setCard(this);
    }

}
