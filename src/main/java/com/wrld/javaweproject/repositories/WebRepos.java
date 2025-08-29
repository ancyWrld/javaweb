package com.wrld.javaweproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wrld.javaweproject.models.WebModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebRepos extends JpaRepository<WebModel, Long> {
    List<WebModel> findByUsernameContainingIgnoreCase(String username);
    
    // Add this method for login
    Optional<WebModel> findByEmail(String email);
}