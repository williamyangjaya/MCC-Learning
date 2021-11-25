/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author LENOVO-KL
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordConfig passwordConfig;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordConfig.authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/trainer").hasRole("TRAINER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/api/employees/create").hasAnyRole("ADMIN", "TRAINER", "TRAINEE")
                .antMatchers("/api/employees/list-all").hasAnyRole("ADMIN", "TRAINER", "TRAINEE")
                .antMatchers("/api/employees/update/{id}").hasAnyRole("ADMIN", "TRAINEE")
                .antMatchers("/api/employees/delete/{id}").hasAnyRole("ADMIN")
                .antMatchers("/trainee").hasAnyRole("TRAINER", "TRAINEE")
                .antMatchers("/login", "/registrationMail", "/loginuser").permitAll()
                .antMatchers("/attendance/**", "/myprofile/**", "/history/**", "/trainee/**", "/trainer/**", "/feedback-form/**").authenticated()
                .and()
                .formLogin().disable()
                .logout().disable()
                .httpBasic();
    }
}
