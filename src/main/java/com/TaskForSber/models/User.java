package com.TaskForSber.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

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

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User() {
    }

    public User(String email, String password, String name, int yearOfBirth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
