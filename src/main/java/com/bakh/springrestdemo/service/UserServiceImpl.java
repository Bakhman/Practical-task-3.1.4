package com.bakh.springrestdemo.service;

import com.bakh.springrestdemo.exception_handler.UserNotFoundException;
import com.bakh.springrestdemo.model.User;
import com.bakh.springrestdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        User getUser = userRepository.getUserById(id);
        if (getUser == null) {
            throw new UserNotFoundException();
        } else {
            return getUser;
        }
    }

    @Override
    public User getUserByUserName(String username) {
        User getUser = userRepository.getUserByUserName(username);
        if (getUser == null) {
            throw new UserNotFoundException();
        } else {
            return getUser;
        }
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {

        User getUser = userRepository.getUserById(id);
        if (getUser == null) {
            throw new UserNotFoundException();
        } else {
            userRepository.updateUser(id, user);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User getUser = userRepository.getUserById(id);
        if (getUser == null) {
            throw new UserNotFoundException();
        } else {
            userRepository.deleteUser(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }
}
