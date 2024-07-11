package com.revature.services;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

//Password encoder just encodes password and then saves it for security purposes
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserDTO userDTO){

        User user = new User(
                userDTO.getUId(),
                userDTO.getUsername(),
                userDTO.getPassword()
  //             this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepo.save(user);

        return user.getUsername();
    }



}
