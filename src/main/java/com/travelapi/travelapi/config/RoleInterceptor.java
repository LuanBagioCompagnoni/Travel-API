package com.travelapi.travelapi.config;

import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.repositories.UserRepository;
import com.travelapi.travelapi.services.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private com.travelapi.travelapi.utils.JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "Authorization header missing or invalid!");
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "Invalid token!");
        }

        User user = userRepository.findByToken(token);
        if (user == null) {
            return writeErrorResponse(response, HttpStatus.UNAUTHORIZED, "Invalid token!");
        }

        if (roleService.validateRoutePermission(request.getRequestURI(), request.getMethod(), user)) {
            return writeErrorResponse(response, HttpStatus.FORBIDDEN, "You cannot access this feature!");
        }

        return true;
    }

    private boolean writeErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.getWriter().write(message);
        return false;
    }
}
