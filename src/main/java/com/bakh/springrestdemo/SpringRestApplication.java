package com.bakh.springrestdemo;

import com.bakh.springrestdemo.model.Role;
import com.bakh.springrestdemo.model.User;
import com.bakh.springrestdemo.service.RoleService;
import com.bakh.springrestdemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner {

    final UserService userService;
    final RoleService roleService;

    public SpringRestApplication(UserRepository userRepository, RoleRepository roleRepository, UserService userService, RoleService roleService, EntityManager entityManager) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(userService.getAllUsers().isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleService.saveRole(adminRole);
            roleService.saveRole(userRole);

            User admin = new User("Bakhmai", "Begaev", (byte) 49, "admin@mail.ru", "admin");
            admin.addRole(adminRole);

            User user = new User("Иван", "Пшыченко", (byte) 33, "user@mail.ru", "user");
            user.addRole(userRole);

            userService.saveUser(admin);
            userService.saveUser(user);
        }
    }
}
