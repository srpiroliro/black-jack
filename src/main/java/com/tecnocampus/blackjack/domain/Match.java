package com.tecnocampus.blackjack.domain;

import java.util.UUID;

import com.tecnocampus.blackjack.utilities.EMatchType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;


@Entity
@Getter
public class Match {
    private static int TARGET_PLAYING_SCORE=21;

    @Id
    private String id = UUID.randomUUID().toString();
    
    @ManyToOne
    private User user;
    
    private int score;
    private EMatchType status=EMatchType.PLAYING;

    public Match(User user){
        this.user=user;
    }

    public EMatchType play(){
        int generatedCard = generateCard();

        score+=generatedCard;
        updateStatus();
        
        return status; 
    }

    private void updateStatus(){
        if(score==TARGET_PLAYING_SCORE)
            status=EMatchType.WON;
        else if (score>TARGET_PLAYING_SCORE)
            status=EMatchType.LOST;
    }

    private int generateCard(){
        return (int) Math.random()*12+1;
    }

}
