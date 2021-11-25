/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.Data;

@Data
public class LinkUpdateDTO {

    private String erd;
    private String uml;
    private String skema;
    private String link;

    public LinkUpdateDTO() {
    }

    public LinkUpdateDTO(String erd, String uml, String skema, String link) {
        this.erd = erd;
        this.uml = uml;
        this.skema = skema;
        this.link = link;
    }

}
