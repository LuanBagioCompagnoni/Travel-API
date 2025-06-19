package com.travelapi.travelapi.services;

import com.travelapi.travelapi.models.User;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RoleService {

    public boolean validateRoutePermission(String pathCalled, String method, User user) {
        return user.getRole().getRoutes().stream()
                .anyMatch(route -> {
                    if (!route.getMethod().equalsIgnoreCase(method)) return false;

                    // testa o path que chega com o regex da permissão
                    Pattern pattern = Pattern.compile(route.getPath());
                    Matcher matcher = pattern.matcher(pathCalled);
                    return matcher.matches();
                });
    }
}
