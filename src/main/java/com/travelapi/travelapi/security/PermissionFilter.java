package com.travelapi.travelapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapi.travelapi.models.ApiResponse;
import com.travelapi.travelapi.models.User;
import com.travelapi.travelapi.repositories.UserRepository;
import com.travelapi.travelapi.services.RoleService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class PermissionFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            User user = userRepository.findByUsername(username);

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                writeJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                        new ApiResponse<>(false, null, "Invalid token or user not found!"));
                return;
            }

            boolean allowed = roleService.validateRoutePermission(
                    request.getRequestURI(),
                    request.getMethod(),
                    user
            );

            if (!allowed) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                writeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN,
                        new ApiResponse<>(false, null, "You are not authorized to access this route!"));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void writeJsonResponse(HttpServletResponse response, int statusCode, ApiResponse<?> body) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = objectMapper.writeValueAsString(body);
        response.getWriter().write(json);
    }

}
