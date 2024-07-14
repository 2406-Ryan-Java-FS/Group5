package com.revature.controllers;

import com.revature.dto.ReqRes;
import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping(path="/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userServiceImpl.register(reg));
    }

    @PostMapping(path="/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(userServiceImpl.login(req));
    }

    @PostMapping(path="/auth/refresh")
    public ResponseEntity<ReqRes> efreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(userServiceImpl.refreshToken(req));
    }

    @GetMapping(path="/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping(path="/admin/get-users/{id}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }

    @PutMapping(path="/admin/update/{id}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer id, @RequestBody User reqres){
        return ResponseEntity.ok(userServiceImpl.updateUser(id, reqres));
    }

    @GetMapping(path="/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyInformation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ReqRes response = userServiceImpl.getmyInfo(username);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping(path="/admin/delete/{id}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok(userServiceImpl.deleteUser(id));
    }

}
