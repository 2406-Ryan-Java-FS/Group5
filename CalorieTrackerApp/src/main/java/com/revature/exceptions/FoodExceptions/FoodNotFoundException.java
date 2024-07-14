package com.revature.exceptions.FoodExceptions;

import java.util.List;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException(String message){
        super(message);
    }
}
