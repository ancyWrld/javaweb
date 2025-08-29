package com.wrld.javaweproject.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class WebModelDTO {
    
    private Long id;
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phone;

    @NotBlank(message = "National ID is required")
    @Size(min = 5, max = 20, message = "National ID must be between 5 and 20 characters")
    private String nationalId;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    @NotBlank(message = "Region is required")
    @Size(min = 2, max = 100, message = "Region must be between 2 and 100 characters")
    private String region;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    private String password;

    // Constructors
    public WebModelDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}