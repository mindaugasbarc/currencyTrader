package com.learning.spring.user;

import com.learning.spring.user.model.User;
import com.learning.spring.user.model.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserDetails_Username(String username);
}
