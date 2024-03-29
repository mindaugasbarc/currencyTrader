package com.learning.spring.user.model;

import com.learning.spring.currencies.model.Balance;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsImpl userDetails;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User(UserDetailsImpl userDetails, Balance balance) {
        this.userDetails = userDetails;
        this.balance = balance;
    }

    public User() {
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public Balance getBalance() {
        return balance;
    }
}
