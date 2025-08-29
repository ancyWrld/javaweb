package com.wrld.javaweproject.services;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class WebService {

    @Autowired
    private WebRepos repo;

    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<WebModel> users = repo.findAll();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", users);
        response.put("count", users.size());
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getUserById(Long id) {
        WebModel user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", user);
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> createUser(WebModelDTO userDTO) {
        WebModel user = convertToEntity(userDTO);
        WebModel savedUser = repo.save(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User created successfully");
        response.put("data", savedUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Map<String, Object>> updateUser(Long id, WebModelDTO userDTO) {
        WebModel existingUser = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setNationalId(userDTO.getNationalId());
        existingUser.setDob(userDTO.getDob());
        existingUser.setRegion(userDTO.getRegion());
        existingUser.setPassword(userDTO.getPassword());
        
        WebModel updatedUser = repo.save(existingUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User updated successfully");
        response.put("data", updatedUser);
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> deleteUser(Long id) {
        WebModel user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
        repo.delete(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User deleted successfully");
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getUsersCount() {
        long count = repo.count();
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("count", count);
        
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> searchUsers(String username) {
        List<WebModel> users = repo.findByUsernameContainingIgnoreCase(username);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", users);
        response.put("count", users.size());
        
        return ResponseEntity.ok(response);
    }

    private WebModel convertToEntity(WebModelDTO dto) {
        WebModel user = new WebModel();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setNationalId(dto.getNationalId());
        user.setDob(dto.getDob());
        user.setRegion(dto.getRegion());
        user.setPassword(dto.getPassword()); // In real app, hash this password!
        return user;
    }
}