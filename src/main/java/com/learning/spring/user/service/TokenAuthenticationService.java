package com.learning.spring.user.service;

import com.google.common.collect.ImmutableMap;
import com.learning.spring.user.User;
import com.learning.spring.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
final class TokenAuthenticationService implements UserAuthenticationService {
    private final TokenService tokens;
    private final UserRepository userRepository;

    @Autowired
    TokenAuthenticationService(TokenService tokens, UserRepository userRepository) {
        this.tokens = tokens;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> login(final String username, final String password) {
        return Optional.ofNullable(userRepository
                .findByUsername(username))
                .filter(user -> user.getPassword().equals(password))
                .map(user -> tokens.expiring(ImmutableMap.of("username", username)));
    }

    @Override
    public User findByToken(final String token) {
        Map<String, String > result = tokens.verify(token);
        return userRepository.findByUsername(result.get("username"));
    }

    @Override
    public void logout(final User user) {
        // Nothing to doy
    }
}
