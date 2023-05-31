package com.TaskForSber.dto;


public class UserDTO {

    private String name;

    private String password;

    private int year;


    public UserDTO() {
    }

    public UserDTO(String name, String password, int year) {
        this.name = name;
        this.password = password;
        this.year = year;
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
