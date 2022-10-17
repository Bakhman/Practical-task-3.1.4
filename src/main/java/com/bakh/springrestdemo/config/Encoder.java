package com.bakh.springrestdemo.config;

import com.bakh.springrestdemo.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Bakhmai Begaev
 */
@Component
public class Encoder {
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return user;
    }
}
