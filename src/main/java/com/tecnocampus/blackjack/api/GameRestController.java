package com.tecnocampus.blackjack.api;

import org.springframework.web.bind.annotation.RestController;

import com.tecnocampus.blackjack.application.GameController;
import com.tecnocampus.blackjack.application.dto.GameDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class GameRestController {
    private GameController gameController;

    public GameRestController(GameController gameController){
        this.gameController=gameController;
    }


    @GetMapping("/users/{userId}/games")
    public List<GameDTO> getAllGames(@PathVariable String userId) throws Exception {
        return gameController.getGamesByUserId(userId);
    }

    @PostMapping("/users/{userId}/games")
    public GameDTO createGame(@PathVariable String userId, @RequestBody GameDTO gameDTO) throws Exception {
        return gameController.createGame(userId, gameDTO);
    }

    @PostMapping("/users/{userId}/games/{gameId}")
    public GameDTO play(@PathVariable String userId, @PathVariable String gameId) throws Exception {
        return gameController.play(userId, gameId);
    }

    @GetMapping("/users/{userId}/games/{gameId}")
    public GameDTO getGame(@PathVariable String userId, @PathVariable String gameId) throws Exception {
        return gameController.getGameById(gameId);
    }

    @PutMapping("/users/{userId}/games/{id}")
    public GameDTO stand(@PathVariable String userId, @PathVariable String gameId) throws Exception {
        return gameController.stand(userId, gameId);
    }
}