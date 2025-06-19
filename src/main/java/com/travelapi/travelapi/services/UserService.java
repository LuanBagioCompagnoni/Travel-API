package com.travelapi.travelapi.services;

import com.travelapi.travelapi.dto.UserDTO;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        return user;
    }

    public User create(UserDTO userDTO) {
        User user = this.dtoToEntity(userDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public void updateToken(User user, String token) {
        if (user != null) {
            user.setToken(token);
            repository.save(user);
        }
    }
}
