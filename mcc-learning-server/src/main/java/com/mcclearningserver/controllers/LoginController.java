/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.AuthDTO;
import com.mcclearningserver.dto.LoginDTO;
import com.mcclearningserver.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO-KL
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        return loginService.loginUserByUserPassword(loginDTO);
    }

}
