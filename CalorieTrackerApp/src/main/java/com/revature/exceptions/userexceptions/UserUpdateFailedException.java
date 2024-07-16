package com.revature.exceptions.userexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserUpdateFailedException extends RuntimeException {
    public UserUpdateFailedException(String message){
        super(message);
    }
}
