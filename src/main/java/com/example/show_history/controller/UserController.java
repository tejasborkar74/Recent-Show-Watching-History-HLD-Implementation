package com.example.show_history.controller;

import com.example.show_history.dto.UserDTO;
import com.example.show_history.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/all_users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    // TODO: get user with id, update and delete user
}
