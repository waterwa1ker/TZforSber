package com.TaskForSber.dto;



import com.TaskForSber.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookDTO {


    @Column(name = "title")
    @Size(min = 2, max = 70, message = "title's size should be between 2 and 70")
    private String title;

    @Column(name = "author")
    @Size(min = 2, max = 50, message = "author's size should be between 2 and 50")
    private String author;

    @Column(name = "year_of_publication")
    @Min(value = 0, message = "year of publication should be positive number")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "user id shouldn't be null")
    private UserDTO userDTO;


    public BookDTO(String title, String author, int yearOfPublication, UserDTO userDTO) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.userDTO = userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
