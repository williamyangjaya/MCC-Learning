/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import java.util.List;

/**
 *
 * @author LENOVO-KL
 */
public class AuthDTO {

    private String username;
    private Integer idUser;
    private List<String> authorities;

    public AuthDTO(String username, Integer idUser, List<String> authorities) {
        this.username = username;
        this.idUser = idUser;
        this.authorities = authorities;
    }

    public AuthDTO(String username) {
        this.username = username;
    }

    public AuthDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
