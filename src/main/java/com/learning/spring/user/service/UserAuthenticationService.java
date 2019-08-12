package com.learning.spring.user.service;

import com.learning.spring.user.model.User;
import com.learning.spring.user.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserAuthenticationService   {

    Optional<String> login(String username, String password);
    User findByToken(String token);
    void logout(UserDetailsImpl user);
}
