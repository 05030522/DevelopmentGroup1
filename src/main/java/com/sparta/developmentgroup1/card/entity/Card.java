package com.sparta.developmentgroup1.card.entity;

import com.sparta.developmentgroup1.card.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "card") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본 생성자를 만들어줌
public class Card  {

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
    private LocalDateTime deadline; // 시간비교

    @Column(nullable = false)
    private int index;

    @ManyToOne
    @JoinColumn(name = "user_id")//fk
    private User user;

    @ManyToOne
    @JoinColumn(name = "column_id")//fk
    private Column column;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Card(CardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.background = requestDto.getBackground();
        this.developer = requestDto.getDeveloper();
        this.deadline = requestDto.getDeadline();
    }
    public Card update(CardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.background = requestDto.getBackground();
        this.developer = requestDto.getDeveloper();
        this.deadline = requestDto.getDeadline();

        return this;
    }
}

