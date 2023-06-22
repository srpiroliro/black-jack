package com.tecnocampus.blackjack.domain;

import com.tecnocampus.blackjack.application.dto.UserDTO;
import com.tecnocampus.blackjack.utilities.EGameStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class User {
    private static final int MIN_PASS_LENTGH=8;
    private static final int MIN_PASS_NUMERIC_CHAR=1;
    private static final int MAX_PLAYING_MATCHES=3;
    
    @Id
    private String id=UUID.randomUUID().toString();
    
    @OneToMany(mappedBy = "user")
    private List<Game> games = new ArrayList<>();

    private String email;
    private String username;
    private String password;
    private Calendar creationDate = Calendar.getInstance();

    private int activeGameesCounter=0;

    private double winRate;

    public User(UserDTO userDTO) throws Exception {
        username=userDTO.getUsername();
        email=userDTO.getEmail();
        password=verifyPassword(userDTO.getPassword());
    }

    public void updateUser(UserDTO userDTO) throws Exception{
        email=userDTO.getUsername();
        password=verifyPassword(userDTO.getPassword());
    }

    private String verifyPassword(String password) throws Exception{
        if(password.length()<MIN_PASS_LENTGH)
            throw new Exception("Password must be longer than "+MIN_PASS_LENTGH);
        
        String passwordNumericChars=password.replaceAll("[^0-9]+", password);
        if(passwordNumericChars.length()<MIN_PASS_NUMERIC_CHAR)
            throw new Exception("Password must contain atleast "+MIN_PASS_NUMERIC_CHAR+" number");
        
        return password;
    }
    

    public void finishGame() throws Exception {
        if(activeGameesCounter<=0) 
            throw new Exception("cant finish 0 games");

        updateWinRate();

        activeGameesCounter--;
    }

    public void addGame(Game game) throws Exception{
        checkPlayingGameesAvalability();
        activeGameesCounter++;
        games.add(game);
    }
    private void checkPlayingGameesAvalability() throws Exception {
        if(activeGameesCounter+1>MAX_PLAYING_MATCHES)
            throw new Exception("No new gamees available. finish old ones.");
    }

    private void updateWinRate(){
        Long wins = games.stream().filter(m-> (m.getStatus() == EGameStatus.WON)).count();
        Long loses = games.stream().filter(m-> (m.getStatus() == EGameStatus.LOST)).count();

        winRate=wins/loses;
    }

    public Game getGameById(String gameId) throws Exception {
        return games.stream()
                        .filter(m-> m.getId().equals(gameId))
                        .findFirst()
                        .get();
    }

}