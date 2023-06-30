package com.TaskForSber.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

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
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @NotNull(message = "book id shouldn't be null")
    private Book book;

    public Comment() {
    }

    public Comment(String text, int rating, User user, Book book) {
        this.text = text;
        this.rating = rating;
        this.user = user;
        this.book = book;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
