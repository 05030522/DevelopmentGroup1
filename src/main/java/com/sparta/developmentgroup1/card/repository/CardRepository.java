package com.sparta.developmentgroup1.card.repository;

import com.sparta.developmentgroup1.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{

}
