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
public class ResponseFileDTO {
    private String name;
    private String url;
    private String type;
    private long size;
    private String deadline;
    private Date uploadTime;
    private Integer idModule;

    public ResponseFileDTO() {
    }

    public ResponseFileDTO(String name, String url, String type, long size, Integer idModule) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.idModule = idModule;
    }
    
    
}
