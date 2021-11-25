/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class AllProfileDTO {
    private Integer id;
    private String name;
    private String email;
    private String position;
    private String batch;
    private String kelas;

    public AllProfileDTO() {
    }

    public AllProfileDTO(Integer id, String name, String email, String position, String batch, String kelas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.batch = batch;
        this.kelas = kelas;
    }
    
    
}
