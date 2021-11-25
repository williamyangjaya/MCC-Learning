/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

@Data
public class ValidasiDTO {

    private boolean status;
    private String note;

    public ValidasiDTO() {
    }

    public ValidasiDTO(boolean status, String note) {
        this.status = status;
        this.note = note;
    }

}
