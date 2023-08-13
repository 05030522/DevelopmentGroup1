package com.sparta.developmentgroup1.boards.entity;

import com.sparta.developmentgroup1.boards.dto.BoardRequestDto;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "boards")
public class Board  extends Timestamped { //보드 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String backgroundColor;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator; //보드 만든 사람 예시

    @Builder.Default
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<BoardUser> boardUsers = new ArrayList<>();

    // @Builder.Default
    // @OneToMany(mappedBy = "board", orphanRemoval = true)
    // private List<Area> areas = new ArrayList<>();

    public Board(BoardRequestDto requestDto, User creator) {
        this.name = requestDto.getName();
        this.backgroundColor = requestDto.getBackgroundColor();
        this.description = requestDto.getDescription();
        this.creator = creator;
    }

}
