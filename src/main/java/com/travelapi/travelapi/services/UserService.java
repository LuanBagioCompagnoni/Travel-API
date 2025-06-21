package com.travelapi.travelapi.services;

import com.travelapi.travelapi.dto.UserDTO;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.repositories.RoleRepository;
import com.travelapi.travelapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        return user;
    }

    public User create(UserDTO userDTO) {
        if (repository.findByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("Username alread exists!");
        }

        User user = this.dtoToEntity(userDTO);

        user.setRole(roleRepository.findByName(userDTO.getRoleName()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public boolean updateToken(String username, String token) {
        User user = repository.findByUsername(username);

        if (user != null) {
            user.setToken(token);
            repository.save(user);

            return true;
        }

        return false;
    }

    public boolean getUserByToken(String token) {
        User user = repository.findByToken(token);

        return user != null;
    }
}
