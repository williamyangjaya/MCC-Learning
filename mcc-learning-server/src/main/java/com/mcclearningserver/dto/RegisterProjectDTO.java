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

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getIdMccSatu() {
        return idMccSatu;
    }

    public void setIdMccSatu(Integer idMccSatu) {
        this.idMccSatu = idMccSatu;
    }

    public Integer getIdMccDua() {
        return idMccDua;
    }

    public void setIdMccDua(Integer idMccDua) {
        this.idMccDua = idMccDua;
    }

    public Integer getIdMccTiga() {
        return idMccTiga;
    }

    public void setIdMccTiga(Integer idMccTiga) {
        this.idMccTiga = idMccTiga;
    }
}
