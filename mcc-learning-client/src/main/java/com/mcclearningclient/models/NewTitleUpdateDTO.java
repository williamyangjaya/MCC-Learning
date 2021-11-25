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
public class NewTitleUpdateDTO {
    private String judul;
    private String deskripsi;

    public NewTitleUpdateDTO() {
    }

    public NewTitleUpdateDTO(String judul, String deskripsi) {
        this.judul = judul;
        this.deskripsi = deskripsi;
    }
    
    
}
