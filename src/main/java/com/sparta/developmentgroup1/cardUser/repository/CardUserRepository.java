package com.sparta.developmentgroup1.cardUser.repository;

import com.sparta.developmentgroup1.cardUser.entity.CardUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardUserRepository extends JpaRepository<CardUser, Long> {
}
