package com.learning.spring.user.domain.model;

public final class LoginDetails {

    private final String username;
    private final String password;

    public LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
