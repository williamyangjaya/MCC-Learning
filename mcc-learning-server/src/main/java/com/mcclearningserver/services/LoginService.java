/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.AuthDTO;
import com.mcclearningserver.dto.LoginDTO;
import com.mcclearningserver.entities.User;
import com.mcclearningserver.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO-KL
 */
@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UnsupportedOperationException("Username NOT FOUND");
        }
        return user;
    }

    public AuthDTO loginUserByUserPassword(LoginDTO userLoginDto) throws Exception {
        User user = loadUserByUsername(userLoginDto.getUsername());
        if (!(passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))) {
            throw new Exception("Wrong Pasword");
        }
//        if (!userLoginDto.getPassword().equals(user.getPassword())){
//            throw new Exception("Wrong Pasword");
//        }
        //untuk setting session dan atau cookies
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(), //principal, credential, atoritas yg disimpen pada sesi tsb
                userLoginDto.getPassword(),
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken); //beneran set session
        List<String> grantedAuth = new ArrayList<>();
        for (GrantedAuthority auth : user.getAuthorities()) { //loop utk get otoritas dalam list<String>
            grantedAuth.add(auth.getAuthority());
        }
        return new AuthDTO(user.getUsername(), user.getIdUser(), grantedAuth);
    }

}
