/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class TitleUpdateDTO {

    private String title;
    private String description;

    public TitleUpdateDTO() {
    }

    public TitleUpdateDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
