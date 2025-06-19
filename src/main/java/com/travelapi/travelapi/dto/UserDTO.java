package com.travelapi.travelapi.dto;

import com.travelapi.travelapi.models.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO extends UserLoginDTO {
    @Size(min = 3, message = "Password must be at least 3 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    public UserDTO() {
    }

    public UserDTO(Role role, String password) {
        this.role = role;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
