package com.revature.services;


import com.revature.dto.UserDTO;
//import com.revature.repositories.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/*
 * UserService class implements the UserDetailsService interface
 * to provide user-specific data during authentication and authorization.
 * It retrieves user details from the UserRepo based on the username.
 */

//public class UserService implements UserDetailsService {
//
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        return userRepo.findByUsername(username).orElseThrow();
//    }
//
//
//
//}

/*
 * This interface provides methods for registering, logging in, retrieving, updating, and deleting users.
 * It acts as an intermediary between the controller layer and the repository layer, encapsulating the business logic related to users.
 * */
public interface UserService {

    // Creates a new UserEntity object using the UserRepository and returns a UserDTO.
    public UserDTO registerUser(UserDTO userDTO);

    // Retrieves a UserEntity object by username and password using the UserRepository and returns a UserDTO.
    public UserDTO loginUser(UserDTO userDTO);

    // Retrieve all users stored in the DB
    public List<UserDTO> getAllUsers();

    // Retrieves a UserEntity object by its userId using the UserRepository and returns a UserDTO.
    public UserDTO getUserById(Integer uId);

    // Updates a UserEntity using the UserRepository and returns a UserDTO.
    public UserDTO updateUser(Integer uId, UserDTO userDTO);

    // Deletes a UserEntity using the UserRepository.
    public void deleteUser(Integer uId);
}

