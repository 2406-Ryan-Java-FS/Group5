package com.revature.exceptions.profileexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProfileDetailsException extends RuntimeException{
    public InvalidProfileDetailsException(String message) {
        super(message);
    }
}
