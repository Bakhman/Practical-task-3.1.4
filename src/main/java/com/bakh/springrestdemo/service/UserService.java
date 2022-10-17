package com.bakh.springrestdemo.service;

import com.bakh.springrestdemo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
public interface UserService extends UserDetailsService {
    public Set<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByUserName(String username);

    public void saveUser(User user);

    public void updateUser(Long id, User user);

    public void deleteUser(Long id);
}
