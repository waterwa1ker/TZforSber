package com.TaskForSber.dto;


import com.TaskForSber.models.Book;
import com.TaskForSber.models.User;
import jakarta.persistence.Column;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {

    @Column(name = "text")
    @NotNull(message = "text shouldn't be null")
    private String text;

    @Column(name = "rating")
    @Min(value = 1, message = "rating should be more than zero")
    @Max(value = 5, message = "rating should be less than six")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "user id shouldn't be null")
    private UserDTO userDTO;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @NotNull(message = "book id shouldn't be null")
    private BookDTO bookDTO;


    public CommentDTO(String text, int rating, UserDTO userDTO, BookDTO bookDTO) {
        this.text = text;
        this.rating = rating;
        this.userDTO = userDTO;
        this.bookDTO = bookDTO;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}
