/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

/**
 *
 * @author William Yangjaya
 */
@Data
public class ProfileDTO {
    private Integer id;
    private String name;
    private String email;
    private String position;
    private String batch;
    private String kelas;

    public ProfileDTO() {
    }

    public ProfileDTO(String name, String email, String position, String batch, String kelas) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.batch = batch;
        this.kelas = kelas;
    }

    public ProfileDTO(Integer id, String name, String email, String kelas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.kelas = kelas;
    }

    public ProfileDTO(String name, String email, String position) {
        this.name = name;
        this.email = email;
        this.position = position;
    }
    
    
}
