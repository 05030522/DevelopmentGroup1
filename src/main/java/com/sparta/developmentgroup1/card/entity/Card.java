package com.sparta.developmentgroup1.card.entity;


import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import com.sparta.developmentgroup1.cardComment.entity.CardComment;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.sparta.developmentgroup1.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "card") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본 생성자를 만들어줌
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

    @Column(nullable = false)
    private String developer;

    @Column
    private LocalDateTime deadline; // 시간비교. Calender클래스의 getTimeInMills()라는 함수를 이용하여 반화해야됨.. 어떻게하지이~??

    @Column(nullable = false)
    private int position;

    @ManyToOne
    @JoinColumn(name = "user_id")//fk
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")//fk
    private Post post;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<CardComment> comments = new ArrayList<>();

    public Card(CardRequestDto requestDto, Post post, int position){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.background = requestDto.getBackground();
        this.developer = requestDto.getDeveloper();
        this.deadline = requestDto.getDeadline();
        this.post = post;
        this.position = position;

    }
    public Card update(CardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.background = requestDto.getBackground();
        this.developer = requestDto.getDeveloper();
        this.deadline = requestDto.getDeadline();
        return this;
    }

    public void setPost(Post post) { //연관 관계 맵핑
        this.post = post;
    }
}

