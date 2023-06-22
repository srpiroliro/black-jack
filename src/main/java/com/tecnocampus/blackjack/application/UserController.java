package com.tecnocampus.blackjack.application;

import com.tecnocampus.blackjack.application.dto.UserDTO;
import com.tecnocampus.blackjack.domain.User;
import com.tecnocampus.blackjack.persistance.GameRepository;
import com.tecnocampus.blackjack.persistance.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;


@Controller
public class UserController {
    private UserRepository userRepository;
    private GameRepository gameRepository;

    public UserController(UserRepository userRepository, GameRepository gameRepository){
        this.userRepository=userRepository;
        this.gameRepository=gameRepository;
    }

    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = new User(userDTO);
        userRepository.save(user);

        return new UserDTO(user);
    }

    public UserDTO getUserById(String userId) throws Exception {
        return new UserDTO(userRepository.findById(userId).get());
    }

    public List<UserDTO> getAllUsers() throws Exception {
        return userRepository.findAll()
                                .stream()
                                .map(UserDTO::new)
                                .collect(Collectors.toList());
    }

    public UserDTO updateUser(String userId, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userId).get();
        user.updateUser(userDTO);

        userRepository.save(user);

        return new UserDTO(user);
    }

    public void removeUser(String userId) throws Exception {
        userRepository.deleteById(userId);
        gameRepository.deleteByUserId(userId);
    }
}
