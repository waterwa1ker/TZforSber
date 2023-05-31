package com.TaskForSber.controller;

import com.TaskForSber.dto.UserDTO;
import com.TaskForSber.models.User;
import com.TaskForSber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getStatus(){
        return userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    private UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getName(), user.getPassword(), user.getYear());
        return userDTO;
    }

}
