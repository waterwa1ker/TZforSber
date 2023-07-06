package com.TaskForSber.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class UserDTO {

    @Email
    @Column(name = "email")
    @UniqueElements(message = "email should be unique")
    @NotNull(message = "email shouldn't be null")
    private String email;

    @Column(name = "password")
    @Size(min = 8, max = 100, message = "password's size should be between 8 and 100")
    private String password;

    @Column(name = "name")
    @NotNull(message = "name shouldn't be null")
    @Size(min = 2, max = 50, message = "name's size should be between 2 and 50")
    private String name;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "year of birth should be more than 1900")
    private int yearOfBirth;

    @Column(name = "role")
    @Size(min = 4, max = 50, message = "user or admin")
    private String role;


    public UserDTO(String email, String password, String name, int yearOfBirth, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
