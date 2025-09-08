package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.dto.AuthRequest;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    @Autowired private UserRepository userRepo;
    @Autowired private JwtUtil jwtUtil;

    public ResponseEntity<?> register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return ResponseEntity.ok("User registered");
    }

    public ResponseEntity<?> authenticate(AuthRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
