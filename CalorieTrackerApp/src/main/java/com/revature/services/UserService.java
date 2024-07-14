package com.revature.services;


import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/*
 * UserService class implements the UserDetailsService interface
 * to provide user-specific data during authentication and authorization.
 * It retrieves user details from the UserRepo based on the username.
 */


public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepo.findByUsername(username).orElseThrow();
    }



}
