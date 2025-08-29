package com.wrld.javaweproject.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "webdata")
public class WebModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(name = "national_id", nullable = false, length = 20)
    private String nationalId;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String password;

    // Constructors
    public WebModel() {}

    public WebModel(String username, String email, String phone, String nationalId, 
                   LocalDate dob, String region, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.nationalId = nationalId;
        this.dob = dob;
        this.region = region;
        this.password = password;
    }

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