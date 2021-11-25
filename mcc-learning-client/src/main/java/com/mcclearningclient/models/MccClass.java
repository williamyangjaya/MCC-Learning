/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

/**
 *
 * @author William Yangjaya
 */
@Data
public class MccClass {

    Integer idClass;
    String name;

    public MccClass() {
    }

    public MccClass(Integer idClass, String name) {
        this.idClass = idClass;
        this.name = name;
    }

}
