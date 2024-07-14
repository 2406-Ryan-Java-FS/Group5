package com.revature.exceptions.profileexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompleteProfileDetailsException extends RuntimeException{
    public IncompleteProfileDetailsException(String message) {
        super(message);
    }
}
