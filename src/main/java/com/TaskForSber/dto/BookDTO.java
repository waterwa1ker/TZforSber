package com.TaskForSber.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO {

    @Size(min = 3, max = 50, message = "Title' size should be between 3 and 50")
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    @Size(min = 3, max = 40, message = "Author's name should be between 3 and 40")
    private String author;

    @Column(name = "year")
    @Min(value = 1000, message = "year should be greater than 1000!")
    private int year;

    @Column(name = "isfree")
    @NotNull(message = "isFree shouldn't be null!")
    private boolean isFree;

    public BookDTO() {
    }

    public BookDTO(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
