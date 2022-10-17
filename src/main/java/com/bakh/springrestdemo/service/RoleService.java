package com.bakh.springrestdemo.service;

import com.bakh.springrestdemo.model.Role;

import java.util.List;
import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
public interface RoleService {
    void saveRole(Role role);

    Set<Role> findAllRolesById(int[] roles);

    public Role findRoleById(int role);

    public Set<Role> findAllRoles();
}
