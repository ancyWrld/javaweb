package com.wrld.javaweproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wrld.javaweproject.dto.WebModelDTO;
import com.wrld.javaweproject.services.WebService;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@Validated
@CrossOrigin(origins = "*")
public class WebControl {

    @Autowired
    private WebService webService;

    // Get all users
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return webService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        return webService.getUserById(id);
    }

    // Create new user
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody WebModelDTO userDTO) {
        return webService.createUser(userDTO);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(
            @RequestParam String email, 
            @RequestParam String password) {
        return webService.verifyLogin(email, password);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody WebModelDTO userDTO) {
        return webService.updateUser(id, userDTO);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        return webService.deleteUser(id);
    }

    // Get users count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> getUsersCount() {
        return webService.getUsersCount();
    }

    // Search users by username
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchUsers(@RequestParam String username) {
        return webService.searchUsers(username);
    }
}