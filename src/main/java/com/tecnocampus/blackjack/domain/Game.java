package com.tecnocampus.blackjack.domain;

import java.util.UUID;

import com.tecnocampus.blackjack.utilities.EGameStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;


@Entity
@Getter
public class Game {
    private static int TARGET_PLAYING_SCORE=21;

    @Id
    private String id = UUID.randomUUID().toString();
    
    @ManyToOne
    private User user;
    
    private int score;
    private EGameStatus status=EGameStatus.PLAYING;

    public Game(User user){
        this.user=user;
    }

    public EGameStatus play() throws Exception {
        checkGamePlayable();

        int generatedCard = generateCard();

        score+=generatedCard;
        updateStatus();
        
        return status; 
    }

    public EGameStatus stand() throws Exception{
        checkGamePlayable();

        int generatedCard = generateCard()+12;
        updateStatusStand(generatedCard);
        
        return status;

    }

    private void updateStatusStand(int generatedCard){
        if(generatedCard>TARGET_PLAYING_SCORE)
            status=EGameStatus.WON;
        else if (generatedCard==score)
            status=EGameStatus.DRAW;
        else
            status=EGameStatus.LOST;
    }

    private void updateStatus(){
        if(score==TARGET_PLAYING_SCORE)
            status=EGameStatus.WON;
        else if (score>TARGET_PLAYING_SCORE)
            status=EGameStatus.LOST;
    }

    private int generateCard(){
        return (int) Math.random()*12+1;
    }

    private void checkGamePlayable() throws Exception {
        if(status!=EGameStatus.PLAYING)
            throw new Exception("bad bad");
    }

}
