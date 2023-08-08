package com.sparta.developmentgroup1.card.entity;

import com.sparta.developmentgroup1.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Table(name = "card") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본 생성자를 만들어줌
public class Card {

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

    @Column(nullable = false)
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")//fk
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")//fk
    private Post post;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void setUser(User user) { //연관 관계 맵핑
        this.user = user;
    }

    public void setPost(Post post) { //연관 관계 맵핑
        this.post = post;
    }

}
