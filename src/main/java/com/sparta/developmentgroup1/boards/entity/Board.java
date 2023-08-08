package com.sparta.developmentgroup1.boards.entity;

import com.sparta.developmentgroup1.common.entity.Timestamped;
import jakarta.persistence.*;

@Entity
public class Board  extends Timestamped { //보드 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String backgroundColor;
    private String description;


/*    @ManyToOne
    private User creator; //보드 만든 사람 예시

    @ManyToMany
    private Set<User> collaborators; //보드에 참여할 사람 예시*/
}
