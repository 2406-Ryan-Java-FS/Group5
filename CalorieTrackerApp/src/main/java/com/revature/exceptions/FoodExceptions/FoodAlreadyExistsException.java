package com.revature.exceptions.FoodExceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class FoodAlreadyExistsException extends RuntimeException{
    public FoodAlreadyExistsException(String message){
        super(message);
    }
}
