/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

/**
 *
 * @author William Yangjaya
 */
public class Class {

    private Integer idClass;
    private String name;

    public Class() {
    }

    public Class(Integer idClass, String name) {
        this.idClass = idClass;
        this.name = name;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
