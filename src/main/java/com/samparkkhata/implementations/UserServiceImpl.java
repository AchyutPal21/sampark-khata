package com.samparkkhata.implementations;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.samparkkhata.entities.User;
import com.samparkkhata.helpers.ResourceNotFoundException;
import com.samparkkhata.repositories.UserRepository;
import com.samparkkhata.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Constructor Injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public Optional<User> getUserByPhoneNumber(String userPhoneNumber) {
        return userRepository.findByUserPhoneNumber(userPhoneNumber);

    }

    @Override
    public Optional<User> updateUser(User user) {
        User u = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        u.setUserFirstName(user.getUserFirstName());
        u.setUserLastName(user.getUserLastName());
        u.setUserPhoneNumber(user.getUserPhoneNumber());
        u.setUserEmail(user.getUserEmail());
        u.setUserAbout(user.getUserAbout());

        // Save to the database
        User updatedUserData = userRepository.save(u);
        return Optional.ofNullable(updatedUserData);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        userRepository.delete(user);
    }

    @Override
    public boolean isUserExistByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        return user != null ? true : false;
    }

    @Override
    public boolean isUserExistByUserEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElse(null);

        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
