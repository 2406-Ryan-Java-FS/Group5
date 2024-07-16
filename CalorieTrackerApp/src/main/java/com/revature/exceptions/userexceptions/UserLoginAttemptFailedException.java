package com.revature.exceptions.userexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserLoginAttemptFailedException extends RuntimeException {
    public UserLoginAttemptFailedException(String message){
        super(message);
    }
}
