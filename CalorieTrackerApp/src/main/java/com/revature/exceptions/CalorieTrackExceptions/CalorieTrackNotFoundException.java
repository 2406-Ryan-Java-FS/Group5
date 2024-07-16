package com.revature.exceptions.CalorieTrackExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CalorieTrackNotFoundException extends RuntimeException {
    public CalorieTrackNotFoundException(String message){
        super(message);
    }

}
