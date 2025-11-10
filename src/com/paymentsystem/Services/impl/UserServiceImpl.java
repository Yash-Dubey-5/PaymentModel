package com.paymentsystem.Services.impl;

import com.paymentsystem.exception.InvalidLoginException;
import com.paymentsystem.exception.UserAlreadyExistsException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.User;
import com.paymentsystem.repository.IUserRepository;
import com.paymentsystem.Services.IUserService;
import com.paymentsystem.util.IdGenerator;

import java.util.Optional;
public class UserServiceImpl implements IUserService{
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User register(String name,String email,String password)throws UserAlreadyExistsException{

        Optional<User> existingUser=this.userRepository.findByEmail(email);

        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Email '"+ email + "'is already registered");
        }

        String newUserId= IdGenerator.generateUserId();

        User newUser=new User(newUserId, name, email, password);

        return this.userRepository.save(newUser);
    }

    @Override
    public User login(String email,String password)throws UserNotFoundException, InvalidLoginException{

        Optional<User> optionalUser=this.userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("No user found with email:" + email);
        }

        User user = optionalUser.get();

        if(user.getPassword().equals(password)){
            return user;
        }else{
            throw new InvalidLoginException(("Invalid password."));
        }
    }
}
