/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.List;
import lombok.Data;

/**
 *
 * @author William Yangjaya
 */
@Data
public class AuthResponse {

    private String username;
    private Integer idUser;
    private List<String> authorities;

    public AuthResponse() {
    }

    public AuthResponse(String username, Integer idUser, List<String> authorities) {
        this.username = username;
        this.idUser = idUser;
        this.authorities = authorities;
    }

    public AuthResponse(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
