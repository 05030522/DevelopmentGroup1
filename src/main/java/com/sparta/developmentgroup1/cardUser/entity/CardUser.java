package com.sparta.developmentgroup1.cardUser.entity;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "card_users")
@NoArgsConstructor
public class CardUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User collaborator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    public CardUser(User collaborator, Card card) {
        this.collaborator = collaborator;
        this.card = card;
    }

}
