/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import java.util.Date;

/**
 *
 * @author ASUS
 */
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

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
