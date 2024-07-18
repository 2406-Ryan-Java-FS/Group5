package com.revature.services.impl;

import com.revature.dto.UserDTO;
import com.revature.exceptions.userexceptions.InvalidUserCredentialsException;
import com.revature.exceptions.userexceptions.UserNotFoundException;
import com.revature.exceptions.userexceptions.UsernameAlreadyExistsException;
import com.revature.services.UserService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
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
public class UserServiceImpl implements UserService {

    // initiate UserRepository
    private final UserRepo userRepository;

    @Autowired
    /*
     * The UserRepository interface is injected as a dependency (@autowired) to this class using Constructor injection.*/
    public UserServiceImpl(UserRepo userRepository){
        this.userRepository = userRepository;
    }

    @Override
    // Takes a userDTO object as its parameter(the userDTO object contains getUsername() and get.Password() methods)
    // and returns a userDTO object. Which will be handled in the controller methods.
    public UserDTO registerUser(UserDTO userDTO) {

        /* Uses the userRepo's findByUsername() method to see if a user/userDTO object already exists by comparing usernames.
         * findByUsername() method returns an Optional<UserEntity> object, which may or may not contain a UserEntity instance.
         * The isPresent() method provided by the userRepo interface checks if the Optional object contains a value.
         * I use the userDTO.getUsername() to get the username.
         * If an existing user is found, throw a usernameAlreadyExistsException.
         */
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        /* This creates a new userEntity object from the convertUserDTOToUserEntity(userDTO) method using the userDTO object parameter.
         * The .builder() methods creates a builder object for constructing the userEntity object.
         * I get the username and the password from the userDTO.
         * The build() method is called to create the UserEntity instance.
         */
        User user = convertUserDTOToUserEntity(userDTO);

        // This line saves the new UserEntity instance to the database using the UserRepository.save() method.
        // The save method returns the saved UserEntity instance, which is stored in the savedUser variable.
        User savedUser = userRepository.save(user);

        /* Using the convertUserEntityToUserDTO() method, we convert the savedUser entity object to a userDTO object
         - Entity object is saved in the DB while its corresponding DTO will be the return value.
         - The converted UserDTO object is returned as the result of the registerUser method.
        */
        return convertUserEntityToUserDTO(savedUser);
    }

    @Override
    // This is the method signature, which takes a UserDTO object as a parameter and returns a UserDTO object.
    public UserDTO loginUser(UserDTO userDTO) {

        // Retrieve a user by their username from the database using the userRepository.findByUsername() method.
        // check if user exists, if they aren't in the DB, return an exception.
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // if the userEntity object's password in the database DOES NOT MATCH the password provided by the user trying to log in, return an exception.
        if (!user.getPassword().equals(userDTO.getPassword())) {
            throw new InvalidUserCredentialsException("Invalid login details used! Try again.");
        }

        // Otherwise, if they exist, convert and return the retrieved userEntity as a userDTO object.
        return convertUserEntityToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers(){

        // Getting all users from the DB using the userRepo.findAll() method and putting them in a list.
        List<User> allUsersEntity = userRepository.findAll();

        List<UserDTO> allUsers = new ArrayList<>();

        for(User user : allUsersEntity){
            UserDTO userDTO = convertUserEntityToUserDTO(user);
            allUsers.add(userDTO);
        }

        return allUsers;
    }
    // This method retrieves a user by their ID. Helpful for User management.
    @Override
    public UserDTO getUserById(Integer uId){
        // Retrieve a user from the DB using the UserRepository.findById() method or else return null
        User userEntity = userRepository.findById(uId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // converting the retrieved user to a userDTO object.
        return convertUserEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO updateUser(Integer uId, UserDTO userDTO){
        // Retrieve the user from the DB using their Id and if they dont exist throw an exception.
        User userEntity = userRepository.findById(uId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // This code checks if the new username is different from the existing username and if a user with the new username already exists.
        // If true, throw an error.
        // This code ensures a user does not update their username to a username that already exists with another user.
        if (!userEntity.getUsername().equals(userDTO.getUsername()) &&
                userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // If the user exists, I can set their new username.
        userEntity.setUsername(userDTO.getUsername());

        // checks if the password is not null and the user's password exists in the DB.
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            // user can update the password.
            userEntity.setPassword(userDTO.getPassword());
        }

        // save the updated user in the DB using userRepository.save() method.
        User updatedUser = userRepository.save(userEntity);

        // convert the updatedUser into a userDTO object.
        return convertUserEntityToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Integer uId) {
        // Check if user exists in the DB using the userRepository.existsById(userId) method or else throw an exception.
        if (!userRepository.existsById(uId)) {
            throw new UserNotFoundException("User not found");
        }

        // otherwise delete the user using the userRepository.deleteById() method.
        userRepository.deleteById(uId);
    }


    private User convertUserDTOToUserEntity(UserDTO userDTO){
        if (userDTO == null) {
            //throw new UserNotFoundException("User not found");
            return new User();
        }
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }

    private UserDTO convertUserEntityToUserDTO(User userEntity) {
        if (userEntity == null) {
            //throw new UserNotFoundException("User not found");
            return new UserDTO();
        }
        return UserDTO.builder()
                .uId(userEntity.getUId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();

    }


//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public ReqRes register(ReqRes registrationRequest){
//        ReqRes resp = new ReqRes();
//
//        try {
//            User user = new User();
//            user.setUsername(registrationRequest.getUsername());
//            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
//            user.setRole(registrationRequest.getRole());
//
//            //NEED MORE IF WE ARE INCLUDING MORE COLUMS IN DB TABLE SUCH AS NAME
////            user.setName(registrationRequest.getName());
//
//
//            User userResult = userRepo.save(user);
//
//            if (userResult.getUId() > 0 ){
//                resp.setUser(userResult);
//                resp.setMessage("User Saved Successfully");
//                resp.setStatusCode(200);
//
//            }
//
//        } catch (Exception e){
//            resp.setStatusCode(500);
//            resp.setError(e.getMessage());
//        }
//        return resp;
//    }
//
//    public ReqRes login(ReqRes loginRequest){
//        ReqRes response = new ReqRes();
//        try {
//            authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//                            loginRequest.getPassword()));
//            var user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow();
//            var jwt = jwtUtils.generateToken(user);
//            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
//            response.setStatusCode(200);
//            response.setToken(jwt);
//            response.setRefreshToken(refreshToken);
//            response.setExpirationTime("24Hrs");
//            response.setMessage("Log in Success");
//
//
//
//        } catch (Exception e){
//            response.setStatusCode(500);
//            response.setMessage(e.getMessage());
//        }
//
//        return response;
//    }
//
//
//    public ReqRes refreshToken(ReqRes refreshTokenRequest){
//        ReqRes response = new ReqRes();
//        try {
//            String username = jwtUtils.extractUsername(refreshTokenRequest.getToken());
//            User user = userRepo.findByUsername(username).orElseThrow();
//            if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)){
//                var jwt = jwtUtils.generateToken(user);
//                response.setStatusCode(200);
//                response.setToken(jwt);
//                response.setRefreshToken(refreshTokenRequest.getToken());
//                response.setExpirationTime("24Hrs");
//                response.setMessage("Successfully Refreshed Token");
//            }
//            response.setStatusCode(200);
//
//            return response;
//
//        } catch (Exception e){
//            response.setStatusCode(500);
//            response.setMessage(e.getMessage());
//
//            return response;
//        }
//    }
//
//    public ReqRes getAllUsers(){
//        ReqRes reqRes = new ReqRes();
//
//        try {
//            List<User> result = userRepo.findAll();
//            if (!result.isEmpty()){
//                reqRes.setUserList(result);
//                reqRes.setStatusCode(200);
//                reqRes.setMessage("Successful");
//            } else {
//                reqRes.setStatusCode(404);
//                reqRes.setMessage("No users found.");
//            }
//            return reqRes;
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMessage("Error occured: " + e.getMessage());
//            return reqRes;
//        }
//    }
//
//    public ReqRes getUserById(Integer id){
//        ReqRes reqRes = new ReqRes();
//
//        try {
//            User userById = userRepo.findById(id)
//                    .orElseThrow(() -> new RuntimeException("User not found."));
//            reqRes.setUser(userById);
//            reqRes.setStatusCode(200);
//            reqRes.setMessage("User with id: " + id + " found successfully.");
//
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMessage("Error occured: " + e.getMessage());
//        }
//        return reqRes;
//    }
//
//
//
//    public ReqRes deleteUser(Integer userId){
//        ReqRes reqRes = new ReqRes();
//        try {
//            Optional<User> userOptional = userRepo.findById(userId);
//            if (userOptional.isPresent()){
//                userRepo.deleteById(userId);
//                reqRes.setStatusCode(200);
//                reqRes.setMessage("User deleted successfully.");
//            } else {
//                reqRes.setStatusCode(404);
//                reqRes.setMessage("User not found for deletion");
//            }
//
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMessage("Error occured while deleting user: " + e.getMessage());
//        }
//        return reqRes;
//    }
//
//    public ReqRes updateUser(Integer userId, User updatedUser){
//        ReqRes reqRes = new ReqRes();
//        try {
//            Optional<User> userOptional = userRepo.findById(userId);
//            if (userOptional.isPresent()){
//                User existingUser = userOptional.get();
//                existingUser.setUsername(updatedUser.getUsername());
//                //Add here any other columns we might want to add to models.User.java
//                //.
//                //.
//
//
//                //Check if password is present in the request
//                if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
//
//                    //Encoding password before updating
//                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//                }
//
//                User savedUser = userRepo.save(existingUser);
//                reqRes.setUser(savedUser);
//                reqRes.setStatusCode(200);
//                reqRes.setMessage("User updated successfully");
//
//            } else {
//                reqRes.setStatusCode(404);
//                reqRes.setMessage("User not found for update");
//            }
//
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMessage("Error occured while updating user: " + e.getMessage());
//        }
//        return reqRes;
//    }
//
//    public ReqRes getmyInfo(String username){
//        ReqRes reqRes = new ReqRes();
//        try {
//            Optional<User> userOptional = userRepo.findByUsername(username);
//            if (userOptional.isPresent()){
//                reqRes.setUser(userOptional.get());
//                reqRes.setStatusCode(200);
//                reqRes.setMessage("Successful");
//            } else {
//                reqRes.setStatusCode(404);
//                reqRes.setMessage("No users found");
//            }
//            return reqRes;
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMessage("Error occured while getting user info: " + e.getMessage());
//            return reqRes;
//        }
//
//    }

}
