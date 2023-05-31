package com.TaskForSber.dto;



public class BookDTO {


    private String title;


    private String author;


    private int year;


    private boolean isFree;


    private UserDTO userDTO;

    public BookDTO() {
    }

    public BookDTO(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public UserDTO getPersonDTO() {
        return userDTO;
    }

    public void setPersonDTO(UserDTO userDTO) {
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
