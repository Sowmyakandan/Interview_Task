package com.example.Project_JWT.controller;

import com.example.Project_JWT.model.User;
import com.example.Project_JWT.payload.JwtRequest;
import com.example.Project_JWT.payload.JwtResponse;
import com.example.Project_JWT.repository.UserRepository;
import com.example.Project_JWT.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ===== Register Page =====
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        User user = new User();
        user.username=username;
        user.password=passwordEncoder.encode(password);
        user.role="ROLE_USER";
        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Login now.");
        return "login";
    }

    // ===== Login Page =====
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        String token = jwtUtil.generateToken(username);
        model.addAttribute("token", token);
        model.addAttribute("username", username);

        return "home";  // secured page
    }
}
