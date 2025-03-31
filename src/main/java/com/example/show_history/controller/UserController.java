package com.example.show_history.controller;

import com.example.show_history.dto.UserDTO;
import com.example.show_history.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity.ok(userService.createUser(userDTO));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/all_users")
    public ResponseEntity<?> getAllUsers(){
        try{
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
