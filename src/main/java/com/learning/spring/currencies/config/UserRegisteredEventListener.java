package com.learning.spring.currencies.config;

import com.google.gson.Gson;
import com.learning.spring.currencies.model.Balance;
import com.learning.spring.user.UserRepository;
import com.learning.spring.user.model.LoginDetails;
import com.learning.spring.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class UserRegisteredEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventListener.class);
    private final static Gson GSON = new Gson();
    private final UserRepository userRepository;

    @Autowired
    public UserRegisteredEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleUserRegisteredEvent(String message) {
        LoginDetails loginDetails = GSON.fromJson(message, LoginDetails.class);
        LOGGER.info("received user registered event");
        userRepository.save(new User(loginDetails.getUsername(), new Balance()));
        LOGGER.info("new user with username: " + loginDetails.getUsername() + "successfully saved");
    }

}