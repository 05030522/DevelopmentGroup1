package com.sparta.developmentgroup1.user.entity;

import com.sparta.developmentgroup1.boards.entity.Board;
import com.sparta.developmentgroup1.boards.entity.BoardUser;
import com.sparta.developmentgroup1.comment.entity.Comment;
import com.sparta.developmentgroup1.cardUser.entity.CardUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Builder.Default
    @OneToMany(mappedBy = "collaborator", orphanRemoval = true)
    private List<BoardUser> boardUsers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "collaborator", orphanRemoval = true) // 작업자 필드명 수정 고려
    private List<CardUser> cardUsers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String password, String nickname, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }
}