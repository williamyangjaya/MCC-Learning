/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

/**
 *
 * @author ASUS
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    
}
