package com.travelapi.travelapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO extends UserLoginDTO {
    @NotNull(message = "Role is required")
    private String roleName;

    public UserDTO() {
    }

    public UserDTO(String role, String password) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
