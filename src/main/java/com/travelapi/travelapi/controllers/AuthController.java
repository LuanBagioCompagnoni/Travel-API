package com.travelapi.travelapi.controllers;

import com.travelapi.travelapi.dto.UserLoginDTO;
import com.travelapi.travelapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authService.processLogin(userLoginDTO);
        System.out.println(token);

        if (token == null) {
            return ResponseEntity.status(401).body("Invalid credentials!");
        }

        return ResponseEntity.ok().body("Bearer " + token);
    }
}
