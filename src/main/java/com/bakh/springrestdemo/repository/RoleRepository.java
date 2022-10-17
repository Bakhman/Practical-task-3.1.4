package com.bakh.springrestdemo.repository;

import com.bakh.springrestdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
public interface RoleRepository {
    void saveRole(Role role);

    Set<Role> findAllRolesById(int[] roles);

    public Role findRoleById(int role);

    public Set<Role> findAllRoles();
}
