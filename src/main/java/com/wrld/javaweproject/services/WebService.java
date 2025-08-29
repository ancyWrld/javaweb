package com.wrld.javaweproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.wrld.javaweproject.models.WebModel;
import com.wrld.javaweproject.dto.WebModelDTO;
import com.wrld.javaweproject.repositories.WebRepos;
import com.wrld.javaweproject.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class WebService {

    @Autowired
    private WebRepos repo;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Get all users
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<WebModel> users = repo.findAll();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", users);
        response.put("count", users.size());
        
        return ResponseEntity.ok(response);
    }

    // Get user by ID
    public ResponseEntity<Map<String, Object>> getUserById(Long id) {
        WebModel user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", user);
        
        return ResponseEntity.ok(response);
    }

    // Create new user
    public ResponseEntity<Map<String, Object>> createUser(WebModelDTO userDTO) {
        // Check if user already exists
        if (repo.findByEmail(userDTO.getEmail()).isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email already registered");
            return ResponseEntity.badRequest().body(response);
        }
        
        WebModel user = convertToEntity(userDTO);
        WebModel savedUser = repo.save(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User created successfully");
        response.put("data", savedUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update user
    public ResponseEntity<Map<String, Object>> updateUser(Long id, WebModelDTO userDTO) {
        WebModel existingUser = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setNationalId(userDTO.getNationalId());
        existingUser.setDob(userDTO.getDob());
        existingUser.setRegion(userDTO.getRegion());
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        WebModel updatedUser = repo.save(existingUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User updated successfully");
        response.put("data", updatedUser);
        
        return ResponseEntity.ok(response);
    }

    // Delete user
    public ResponseEntity<Map<String, Object>> deleteUser(Long id) {
        WebModel user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        repo.delete(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User deleted successfully");
        
        return ResponseEntity.ok(response);
    }

    // Get users count
    public ResponseEntity<Map<String, Object>> getUsersCount() {
        long count = repo.count();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("count", count);
        
        return ResponseEntity.ok(response);
    }

    // Search users by username
    public ResponseEntity<Map<String, Object>> searchUsers(String username) {
        List<WebModel> users = repo.findByUsernameContainingIgnoreCase(username);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", users);
        response.put("count", users.size());
        
        return ResponseEntity.ok(response);
    }

    // Login verification method
    public ResponseEntity<Map<String, Object>> verifyLogin(String email, String password) {
        Optional<WebModel> userOptional = repo.findByEmail(email);
        
        Map<String, Object> response = new HashMap<>();
        
        if (userOptional.isEmpty()) {
            response.put("status", "error");
            response.put("message", "User not found. Please register.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
        WebModel user = userOptional.get();
        
        if (passwordEncoder.matches(password, user.getPassword())) {
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("data", user);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    private WebModel convertToEntity(WebModelDTO dto) {
        WebModel user = new WebModel();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setNationalId(dto.getNationalId());
        user.setDob(dto.getDob());
        user.setRegion(dto.getRegion());
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}