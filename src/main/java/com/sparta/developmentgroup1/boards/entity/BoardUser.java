package com.sparta.developmentgroup1.boards.entity;

import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "board_users")
public class BoardUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator_id", nullable = false)
    private User collaborator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public BoardUser(Board board, User collaborator) {
        this.board = board;
        this.collaborator = collaborator;
    }

    public void updateCollaborator(User newCollaborator) {
        if (this.collaborator != null) {
            this.collaborator.getBoardUsers().remove(this);
        }
        this.collaborator = newCollaborator;
        newCollaborator.getBoardUsers().add(this);
    }
}