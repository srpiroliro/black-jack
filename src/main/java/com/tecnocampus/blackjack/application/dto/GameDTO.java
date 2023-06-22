package com.tecnocampus.blackjack.application.dto;

import com.tecnocampus.blackjack.domain.Game;

import lombok.Getter;


@Getter
public class GameDTO {
    private String id;
    
    private int score;
    private String status;

    public GameDTO(Game game){
        id=game.getId();
        score=game.getScore();
        status=game.getStatus().name();
    }
}