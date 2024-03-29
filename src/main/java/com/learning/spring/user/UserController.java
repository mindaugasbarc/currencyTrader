package com.learning.spring.user;

import com.learning.spring.currencies.model.Balance;
import com.learning.spring.currencies.model.Currency;
import com.learning.spring.currencies.model.Money;
import com.learning.spring.user.model.User;
import com.learning.spring.user.model.UserDetailsImpl;
import com.learning.spring.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(UserRepository userRepository, UserAuthenticationService userAuthenticationService) {
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/register")
    String register(
            @RequestBody UserDetailsImpl userDetails) {

        userRepository.save(new User(userDetails, new Balance()));

        return login(userDetails);
    }

    @PostMapping("/login")
    String login(@RequestBody UserDetailsImpl user) {
        return userAuthenticationService
                .login(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @GetMapping("/balance")
    public Map<String, Double> getBalance(@RequestHeader("Authorization") String authToken) {
        return userAuthenticationService.findByToken(authToken).getBalance().getAccountBalance().stream()
                .collect(Collectors.toMap(entry -> entry.getCurrency().getName(), Money::getAmount));
    }
}
