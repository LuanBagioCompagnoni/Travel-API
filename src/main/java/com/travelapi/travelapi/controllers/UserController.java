package com.travelapi.travelapi.controllers;

import com.travelapi.travelapi.dto.UserDTO;
import com.travelapi.travelapi.models.ApiResponse;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> create(@Valid @RequestBody UserDTO userDTO) {
        try {
            User newUser = service.create(userDTO);
            ApiResponse<User> response = new ApiResponse<>(true, newUser, "User created successfully!");
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ApiResponse<User> response = new ApiResponse<>(false, null, e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ApiResponse<User> response = new ApiResponse<>(false, null, "Erro ao criar usuário");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
