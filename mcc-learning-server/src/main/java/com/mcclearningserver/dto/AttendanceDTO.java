/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

/**
 *
 * @author LENOVO-KL
 */
public class AttendanceDTO {

    private Integer idEmployee;
    private String startDate;
    private String endDate;

    public AttendanceDTO() {
    }

    public AttendanceDTO(Integer idEmployee, String startDate, String endDate) {
        this.idEmployee = idEmployee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
