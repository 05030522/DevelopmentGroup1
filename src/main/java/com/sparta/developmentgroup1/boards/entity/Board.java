package com.sparta.developmentgroup1.boards.entity;

import com.sparta.developmentgroup1.boards.dto.BoardRequestDto;
import com.sparta.developmentgroup1.common.entity.Timestamped;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "author_id", nullable = false)
    private User creator; //보드 만든 사람 예시

    @Builder.Default
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<BoardUser> boardUsers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Area> areas = new ArrayList<>();

    @ManyToMany
    private Set<User> collaborators; //보드 공유하는 사람 예시

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }

    public void updateName(BoardRequestDto boardRequestDto) {
        this.name = boardRequestDto.getName();
    }

    public void updateBackgroundColor(BoardRequestDto boardRequestDto) {
        this.backgroundColor = boardRequestDto.getBackgroundColor();
    }

    public void updateDescription(BoardRequestDto boardRequestDto) {
        this.description = boardRequestDto.getDescription();
    }

}
