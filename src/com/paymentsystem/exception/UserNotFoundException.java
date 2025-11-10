package com.paymentsystem.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message){
        super(message);
    }
}
