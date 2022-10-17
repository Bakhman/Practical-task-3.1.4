package com.bakh.springrestdemo.repository;

import com.bakh.springrestdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
public interface UserRepository {
    public Set<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByUserName(String username);

    public void saveUser(User user);

    public void updateUser(Long id, User user);

    public void deleteUser(Long id);
}
