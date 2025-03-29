package com.example.show_history.service;

import com.example.show_history.dto.UserDTO;
import com.example.show_history.entity.User;
import com.example.show_history.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDTO userEntityToUserDTO(User user) {
        return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::userEntityToUserDTO)
                .toList();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User newUser = new User(userDTO.getUsername(), userDTO.getEmail());
        User savedUser = userRepository.save(newUser);
        return userEntityToUserDTO(savedUser);
    }


}