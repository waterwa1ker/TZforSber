package com.TaskForSber.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Size(min = 3, max = 40, message = "Name's size should be between 3 and 40 symbols")
    @Column(name = "name")
    @UniqueElements(message = "This name already exists!")
    private String name;

    @Column(name = "password")
    @Size(min = 4, max = 30, message = "Password' length should be between 4 and 30 symbols")
    private String password;

    @Min(value = 1)
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    public User() {
    }

    public User(String name, String password, int year) {
        this.name = name;
        this.password = password;
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
