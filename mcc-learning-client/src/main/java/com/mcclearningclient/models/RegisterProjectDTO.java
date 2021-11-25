/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

@Data
public class RegisterProjectDTO {

    private String judul;
    private String deskripsi;
    private Integer idMccSatu;
    private Integer idMccDua;
    private Integer idMccTiga;

    public RegisterProjectDTO() {
    }

    public RegisterProjectDTO(String judul, String deskripsi, Integer idMccSatu, Integer idMccDua, Integer idMccTiga) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.idMccSatu = idMccSatu;
        this.idMccDua = idMccDua;
        this.idMccTiga = idMccTiga;
    }

}
