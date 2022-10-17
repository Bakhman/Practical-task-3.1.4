package com.bakh.springrestdemo.repository;

import com.bakh.springrestdemo.config.Encoder;
import com.bakh.springrestdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bakhmai Begaev
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private Encoder encoder;


    public UserRepositoryImpl(Encoder encoder) {
        this.encoder = encoder;
    }


    @Override
    public Set<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUserName(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email =: username", User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        encoder.passwordCoder(user);
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setId(id);
        encoder.passwordCoder(user);
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }
}
