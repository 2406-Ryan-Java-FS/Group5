package com.revature.controllers;

import com.revature.dto.UserDTO;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("app/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(path="/register")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String u_id = userService.registerUser(userDTO);
        return u_id;

    }




}