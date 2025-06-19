package com.travelapi.travelapi.services;

import com.travelapi.travelapi.dto.UserLoginDTO;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    private com.travelapi.travelapi.utils.JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public String processLogin(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByUsername(userLoginDTO.getUsername());

        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getUsername());

        userService.updateToken(user, token);

        return token;
    }
}
