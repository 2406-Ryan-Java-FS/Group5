package com.revature.services;

import com.revature.dto.UserDTO;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO);

    // Retrieves a UserEntity object by username and password using the UserRepository and returns a UserDTO.
    public UserDTO loginUser(UserDTO userDTO);
}
