package com.revature.controllers;

//import com.revature.dto.ReqRes;
import com.revature.dto.UserDTO;
import com.revature.exceptions.userexceptions.*;
//import com.revature.models.User;
import com.revature.services.UserService;
//import com.revature.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){

        try{
            UserDTO registeredUser = userService.registerUser(userDTO);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (UsernameAlreadyExistsException e){
            throw e;
        } catch(Exception e){
            throw new UserCreationFailedException("Failed to create a new user.");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTO){

        try{
            UserDTO loggedUser = userService.loginUser(userDTO);
            return ResponseEntity.ok(loggedUser);
        } catch (UserNotFoundException | InvalidUserCredentialsException e) {
            throw e; // These will be handled by a global exception handler
        }catch (Exception e) {
            throw new UserLoginAttemptFailedException("Failed to log in.");
        }
    }

    @GetMapping("/{uId}/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(@PathVariable Integer uId){
         UserDTO user = userService.getUserById(uId);

        if (!user.getRole().equals("ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        // Call the userService.getAllUsers() method that returns a list of users and save it in the allUsers list
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    // get user by specifying their id
    @GetMapping("/{uId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int uId){
        try {
            UserDTO user = userService.getUserById(uId);
            if (!user.getRole().equals("ADMIN")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            throw e;
        }
    }

    // update user
    @PutMapping("/{uId}")
    public ResponseEntity<UserDTO>updateUser(@PathVariable int uId, @RequestBody UserDTO userDTO){

        try{
            UserDTO updatedUser = userService.updateUser(uId, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException | UsernameAlreadyExistsException e) {
            throw e;
        } catch(Exception e){
            throw new UserUpdateFailedException("Failed to update new user");
        }


    }

    @DeleteMapping("/{uId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int uId) {

        try{
            userService.deleteUser(uId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            throw e; // This will be handled by a global exception handler
        } catch (Exception e){
            throw new UserDeletionFailedException("Failed to delete user");
        }
    }


//    @PostMapping(path="/auth/register")
//    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
//        return ResponseEntity.ok(userServiceImpl.register(reg));
//    }
//
//    @PostMapping(path="/auth/login")
//    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
//        return ResponseEntity.ok(userServiceImpl.login(req));
//    }
//
//    @PostMapping(path="/auth/refresh")
//    public ResponseEntity<ReqRes> efreshToken(@RequestBody ReqRes req){
//        return ResponseEntity.ok(userServiceImpl.refreshToken(req));
//    }
//
//    @GetMapping(path="/admin/get-all-users")
//    public ResponseEntity<ReqRes> getAllUsers(){
//        return ResponseEntity.ok(userServiceImpl.getAllUsers());
//    }
//
//    @GetMapping(path="/admin/get-users/{id}")
//    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer id){
//        return ResponseEntity.ok(userServiceImpl.getUserById(id));
//    }
//
//    @PutMapping(path="/admin/update/{id}")
//    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer id, @RequestBody User reqres){
//        return ResponseEntity.ok(userServiceImpl.updateUser(id, reqres));
//    }
//
//    @GetMapping(path="/adminuser/get-profile")
//    public ResponseEntity<ReqRes> getMyInformation(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        ReqRes response = userServiceImpl.getmyInfo(username);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @DeleteMapping(path="/admin/delete/{id}")
//    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer id){
//        return ResponseEntity.ok(userServiceImpl.deleteUser(id));
//    }

}
