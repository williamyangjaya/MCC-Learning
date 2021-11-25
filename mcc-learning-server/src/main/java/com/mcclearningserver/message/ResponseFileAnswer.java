/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.message;

import java.util.Date;

/**
 *
 * @author LenovoX280
 */
public class ResponseFileAnswer {
    private String name;
    private String url;
    private String type;
    private long size;
    private Integer idEmployee;
    private String examName;
    private Date deadline;
    private Date uploadTime;
    

    public ResponseFileAnswer() {
    }

    public ResponseFileAnswer(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public ResponseFileAnswer(String name, String url, String type, long size, Integer idEmployee, String examName, Date deadline, Date uploadTime) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.idEmployee = idEmployee;
        this.examName = examName;
        this.deadline = deadline;
        this.uploadTime = uploadTime;
    }

  

   
    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }



    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    
    

    
}
