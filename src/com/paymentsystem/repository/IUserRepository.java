package com.paymentsystem.repository;

import com.paymentsystem.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String userId);

    List<User>findAll();
}
