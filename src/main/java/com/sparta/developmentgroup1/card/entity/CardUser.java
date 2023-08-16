package com.sparta.developmentgroup1.card.entity;

import com.sparta.developmentgroup1.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CardUserEntity")
@Getter
@Setter
@Table(name = "card_users")
@NoArgsConstructor
public class CardUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    public CardUser(Card card, User member) {
        this.card = card;
        this.member = member;
    }

}
