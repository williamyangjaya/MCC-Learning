/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author ACER
 */
//@ConfigurationProperties(prefix = "file")
public class FileStorageProperty {

    private String uploadDir;
    private String moduleDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getModuleDir() {
        return moduleDir;
    }

    public void setModuleDir(String moduleDir) {
        this.moduleDir = moduleDir;
    }

}
