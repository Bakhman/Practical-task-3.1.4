package com.bakh.springrestdemo.config;

import com.bakh.springrestdemo.service.UserService;
import com.bakh.springrestdemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Bakhmai Begaev
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserServiceImpl userService;
    private final Encoder encoder;

    @Autowired
    public SecurityConfig(SuccessUserHandler successUserHandler, @Lazy UserServiceImpl userService, Encoder encoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();
    }



    @Autowired
    protected void configureGlobalSecurity(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserService> daoAuthenticationConfigurer =
                authenticationManagerBuilder.userDetailsService(userService);
        daoAuthenticationConfigurer.passwordEncoder(encoder.passwordEncoder());

    }
}
