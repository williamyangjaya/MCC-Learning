/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class HistoryDTO {
    private Integer idHistory;
    private Integer idProject;
    private String info;
    private Date waktu;
    private String pesan;
    private String status;

    public HistoryDTO() {
    }

    public HistoryDTO(Integer idHistory, Integer idProject, Date waktu, String pesan, String status) {
        this.idHistory = idHistory;
        this.idProject = idProject;
        this.waktu = waktu;
        this.pesan = pesan;
        this.status = status;
    }
    
    
}
