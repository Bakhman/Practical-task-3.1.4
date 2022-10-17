package com.bakh.springrestdemo.repository;

import com.bakh.springrestdemo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bakhmai Begaev
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Set<Role> findAllRolesById(int[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < roles.length; i++) {
            roleSet.add(entityManager.createQuery("SELECT r FROM Role r WHERE r.id=:roles", Role.class).setParameter("roles", roles[i]).getSingleResult());
        }
        return roleSet;
    }

    @Override
    public Role findRoleById(int role) {
        Role roleById = entityManager.createQuery("SELECT r FROM Role r WHERE r.id=:role", Role.class).setParameter("role", role).getSingleResult();
        return roleById;
    }

    @Override
    public Set<Role> findAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
