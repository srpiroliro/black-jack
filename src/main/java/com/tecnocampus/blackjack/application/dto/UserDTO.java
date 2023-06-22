package com.tecnocampus.blackjack.application.dto;

import com.tecnocampus.blackjack.domain.User;
import com.tecnocampus.blackjack.utilities.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDTO {
    private String id;
    
    private String email;
    private String username;
    private String password;
    private String creationDate;


    public UserDTO(User user) throws Exception {
        id=user.getId();
        username=user.getUsername();
        email=user.getEmail();
        password=user.getPassword();
        creationDate=Constants.dateFormat.format(user.getCreationDate().getTime());
    }
}