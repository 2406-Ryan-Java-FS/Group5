package com.revature.services;


import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

//    String registerUser(UserDTO userDTO);
//
//    User registerUser(String userDTO);
//
//    User findByUsername(String username);
//
//    void updateUser(User user);
//
//    void deleteUser(Long userId);
//
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepo.findByUsername(username).orElseThrow();
    }



}
