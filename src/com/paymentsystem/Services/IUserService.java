package com.paymentsystem.Services;

import com.paymentsystem.exception.InvalidLoginException;
import com.paymentsystem.exception.UserAlreadyExistsException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.User;

public interface IUserService {
    
    User register(String name,String email,String password)
        throws UserAlreadyExistsException;

    User login(String email, String password)
        throws UserNotFoundException , InvalidLoginException;
}
