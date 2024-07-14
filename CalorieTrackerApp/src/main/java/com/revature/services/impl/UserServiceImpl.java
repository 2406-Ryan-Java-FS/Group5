package com.revature.services.impl;

import com.revature.config.JWTUtils;
import com.revature.dto.ReqRes;
import com.revature.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;



/*
 * UserServiceImpl class provides the implementation for the UserService interface.
 * It handles user registration, login, and management functionalities such as updating,
 * deleting, and retrieving user information. It also manages JWT token generation and
 * validation for authentication purposes.
 */

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            User user = new User();
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());

            //NEED MORE IF WE ARE INCLUDING MORE COLUMS IN DB TABLE SUCH AS NAME
//            user.setName(registrationRequest.getName());


            User userResult = userRepo.save(user);

            if (userResult.getUId() > 0 ){
                resp.setUser(userResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);

            }

        } catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            var user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Log in Success");



        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }


    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        try {
            String username = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User user = userRepo.findByUsername(username).orElseThrow();
            if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)){
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);

            return response;

        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());

            return response;
        }
    }

    public ReqRes getAllUsers(){
        ReqRes reqRes = new ReqRes();

        try {
            List<User> result = userRepo.findAll();
            if (!result.isEmpty()){
                reqRes.setUserList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found.");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    public ReqRes getUserById(Integer id){
        ReqRes reqRes = new ReqRes();

        try {
            User userById = userRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found."));
            reqRes.setUser(userById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("User with id: " + id + " found successfully.");

        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured: " + e.getMessage());
        }
        return reqRes;
    }



    public ReqRes deleteUser(Integer userId){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()){
                userRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully.");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }

        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, User updatedUser){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()){
                User existingUser = userOptional.get();
                existingUser.setUsername(updatedUser.getUsername());
                //Add here any other columns we might want to add to models.User.java
                //.
                //.


                //Check if password is present in the request
                if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {

                    //Encoding password before updating
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepo.save(existingUser);
                reqRes.setUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");

            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes getmyInfo(String username){
        ReqRes reqRes = new ReqRes();
        try {
            Optional<User> userOptional = userRepo.findByUsername(username);
            if (userOptional.isPresent()){
                reqRes.setUser(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occured while getting user info: " + e.getMessage());
            return reqRes;
        }

    }

}
