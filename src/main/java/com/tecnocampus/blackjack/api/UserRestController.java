package com.tecnocampus.blackjack.api;


import com.tecnocampus.blackjack.application.UserController;
import com.tecnocampus.blackjack.application.dto.UserDTO;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class UserRestController {
    private UserController userController;

    public UserRestController(UserController userController){
        this.userController=userController;
    }


    @GetMapping("/users")
    public List<UserDTO> getAllUsers() throws Exception {
        return userController.getAllUsers();
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception {
        return userController.createUser(userDTO);
    }

    // @GetMapping("/users/{id}")
    // public UserDTO getUser(@PathVariable String id) throws Exception {
    //     return userController.getUserById(id);
    // }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) throws Exception {
        return userController.updateUser(id, userDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) throws Exception {
        userController.removeUser(id);
    }   
}
