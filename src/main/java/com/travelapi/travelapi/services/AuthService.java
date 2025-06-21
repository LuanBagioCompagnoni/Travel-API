package com.travelapi.travelapi.services;

import com.travelapi.travelapi.dto.UserLoginDTO;
import com.travelapi.travelapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public String processLogin(UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);


            String token = jwtUtil.generateToken(userLoginDTO.getUsername());

            boolean isUpdatedToken = userService.updateToken(userLoginDTO.getUsername(), token);

            if (!isUpdatedToken) {
                return null;
            }

            return token;
        }
        catch (Exception e) {
            return null;
        }
    }
}
