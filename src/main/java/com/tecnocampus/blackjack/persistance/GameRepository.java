package com.tecnocampus.blackjack.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.tecnocampus.blackjack.domain.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findByUserId(String usedId);

    void deleteByUserId(String userId);
}