package com.bakh.springrestdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * @author Bakhmai Begaev
 */
@Entity
@Table(name = "users_table")
public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @NotEmpty
    private String firstName;

   @NotEmpty
    private String lastName;

    private byte age;
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String firstName, String lastName, byte age, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
//        return String.format("User{'id'=%d, 'firstName'=%s, 'lastName'=%s, 'age'=%d, 'email'=%s",
//                id, firstName, lastName, age, email);
        String s = "";
        List<String> sort_roles = new ArrayList<>();
        for(Role role: roles) {
            sort_roles.add(role.getRole());
        }
        Collections.sort(sort_roles);
        for (String s1 : sort_roles) {
            s += s1.split("_")[1] + " ";
        }
        return s;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public boolean hasRole(String role) {
        List<String> rL = new ArrayList<>();
        for (Role r : roles) {
            rL.add(r.getRole());
        }
        return rL.contains(role);
    }
}
