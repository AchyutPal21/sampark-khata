package com.samparkkhata.services;

import java.util.List;
import java.util.Optional;

import com.samparkkhata.entities.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> getUserByEmail(String userEmail);

    Optional<User> getUserByPhoneNumber(String userPhoneNumber);

    Optional<User> updateUser(User user);

    void deleteUser(Long userId);

    boolean isUserExistByUserId(Long userId);

    boolean isUserExistByUserEmail(String userEmail);

    List<User> getAllUsers();

    // Will add more methods related to the user when required for different service logic
}
