package com.sparta.developmentgroup1.card.repository;

import com.sparta.developmentgroup1.card.entity.Card;
import com.sparta.developmentgroup1.card.entity.CardUser;
import com.sparta.developmentgroup1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardUserRepository extends JpaRepository<CardUser, Long> {
    CardUser findByCardAndMember(Card card, User member);
}
