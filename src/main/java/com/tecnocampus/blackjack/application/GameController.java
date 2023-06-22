package com.tecnocampus.blackjack.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.tecnocampus.blackjack.application.dto.GameDTO;
import com.tecnocampus.blackjack.domain.Game;
import com.tecnocampus.blackjack.domain.User;
import com.tecnocampus.blackjack.persistance.GameRepository;
import com.tecnocampus.blackjack.persistance.UserRepository;
import com.tecnocampus.blackjack.utilities.EGameStatus;


@Controller
public class GameController {
    private GameRepository gameRepository;
    private UserRepository userRepository;

    public GameController(UserRepository userRepository, GameRepository gameRepository){
        this.userRepository=userRepository;
        this.gameRepository=gameRepository;
    }

    public GameDTO createGame(String userId, GameDTO gameDTO) throws Exception {
        User user = userRepository.findById(userId).get();
        Game game = new Game(user);

        user.addGame(game);    
        gameRepository.save(game);

        return new GameDTO(game);
    }

    public GameDTO play(String userId, String gameId) throws Exception {
        User user = userRepository.findById(userId).get();
        Game game = user.getGameById(gameId);
        
        EGameStatus status = game.play();
        if(status==EGameStatus.WON || status==EGameStatus.LOST)
            user.finishGame();

        return new GameDTO(game);
    }

    public GameDTO stand(String userId, String gameId) throws Exception {
        User user = userRepository.findById(userId).get();
        Game game = user.getGameById(gameId);

        game.stand();
        user.finishGame();

        return new GameDTO(game);
    }

    public GameDTO getGameById(String gameId){
        return new GameDTO(gameRepository.findById(gameId).get());
    }
    
    public List<GameDTO> getGamesByUserId(String userId){
        // CHECK: is array filled?
        User user = userRepository.findById(userId).get();
        return user.getGames().stream().map(GameDTO::new).collect(Collectors.toList());
    }
}
