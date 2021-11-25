/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

/**
 *
 * @author ASUS
 */
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

    public String getErd() {
        return erd;
    }

    public void setErd(String erd) {
        this.erd = erd;
    }

    public String getUml() {
        return uml;
    }

    public void setUml(String uml) {
        this.uml = uml;
    }

    public String getSkema() {
        return skema;
    }

    public void setSkema(String skema) {
        this.skema = skema;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
