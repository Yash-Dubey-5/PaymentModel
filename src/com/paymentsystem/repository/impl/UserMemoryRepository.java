package com.paymentsystem.repository.impl;

import com.paymentsystem.model.User;
import com.paymentsystem.repository.IUserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserMemoryRepository implements IUserRepository {
    private final Map<String,User> userDatabase =new HashMap<>();
    
    @Override
    public User save(User user){
        userDatabase.put(user.getEmail(),user);
        return user;
    }

    @Override
    public Optional<User>findByEmail(String email){
        User user =userDatabase.get(email);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User>findById(String userId){
        for(User user: userDatabase.values()){
            if(user.getUserId().equals(userId)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll(){
        return new ArrayList<>(userDatabase.values());
    }
}
