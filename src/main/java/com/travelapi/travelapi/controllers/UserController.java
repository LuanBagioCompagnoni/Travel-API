package com.travelapi.travelapi.controllers;

import com.travelapi.travelapi.dto.UserDTO;
import com.travelapi.travelapi.models.ApiResponse;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destination")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> review(@Valid @RequestBody UserDTO userDTO) {
        User newUser = service.create(userDTO);

        ApiResponse<User> response = new ApiResponse<>(true, newUser, "User created successfully!");

        return ResponseEntity.ok(response);
    }
}
