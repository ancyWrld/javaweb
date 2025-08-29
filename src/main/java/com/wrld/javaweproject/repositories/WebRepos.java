package com.wrld.javaweproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wrld.javaweproject.models.WebModel;

import java.util.List;

@Repository
public interface WebRepos extends JpaRepository<WebModel, Long> {
    // Custom query for efficient search
    List<WebModel> findByUsernameContainingIgnoreCase(String username);
}