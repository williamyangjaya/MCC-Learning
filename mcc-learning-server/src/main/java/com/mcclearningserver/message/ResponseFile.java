/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author LENOVO-KL
 */
public class ResponseFile {

    private String name;
    private String url;
    private String type;
    private long size;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy, HH:MM:ss")
    private Date deadline;
    private Date uploadTime;
    private Integer idModule;
    
    

    public ResponseFile() {
    }

    public ResponseFile(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public ResponseFile(String name, String url, String type, long size, Date deadline, Date uploadTime) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.deadline = deadline;
        this.uploadTime = uploadTime;
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

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }
    
    

}
