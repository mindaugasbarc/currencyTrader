package com.learning.spring.user;

import com.learning.spring.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserAuthenticationService userAuthenticationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/register")
    String register(
            @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return login(user);
    }

    @PostMapping("/login")
    String login(@RequestBody User user) {
        return userAuthenticationService
                .login(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
