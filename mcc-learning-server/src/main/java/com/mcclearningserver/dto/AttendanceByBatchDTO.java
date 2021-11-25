/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import java.util.Date;

/**
 *
 * @author LENOVO-KL
 */
public class AttendanceByBatchDTO {

    private String batch;
    private String date;

    public AttendanceByBatchDTO() {
    }

    public AttendanceByBatchDTO(String batch, String date) {
        this.batch = batch;
        this.date = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
