package com.bakh.springrestdemo.service;

import com.bakh.springrestdemo.model.Role;
import com.bakh.springrestdemo.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Bakhmai Begaev
 */
@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.saveRole(role);
    }

    @Override
    public Set<Role> findAllRolesById(int[] roles) {
        return roleRepository.findAllRolesById(roles);
    }

    public Role findRoleById(int roleId) {
        return roleRepository.findRoleById(roleId);
    }

    @Override
    public Set<Role> findAllRoles() {
        return roleRepository.findAllRoles();
    }
}
