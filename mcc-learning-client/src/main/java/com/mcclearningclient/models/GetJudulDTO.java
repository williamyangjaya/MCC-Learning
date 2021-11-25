/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.List;
import lombok.Data;


@Data
public class GetJudulDTO {
    private Integer idProject;
    private String judul;
    private String batch;
    private List<String> nama;
    private String trainer;
    private String deskripsi;

    public GetJudulDTO() {
    }

    public GetJudulDTO(Integer idProject, String judul, String batch, List<String> nama, String trainer, String deskripsi) {
        this.idProject = idProject;
        this.judul = judul;
        this.batch = batch;
        this.nama = nama;
        this.trainer = trainer;
        this.deskripsi = deskripsi;
    }
    
}
