/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.List;
import lombok.Data;

@Data
public class ProjectDTO {

    private Integer idProject;
    private String title;
    private String decription;
    private String erd;
    private String uml;
    private String skema;
    private String link;
    private List<String> name;
    private String trainer;
    private String batch;

    public ProjectDTO() {
    }

    //admin - data project
    public ProjectDTO(Integer idProject, String title, String decription, String erd, String uml, String skema, String link, List<String> name, String trainer) {
        this.idProject = idProject;
        this.title = title;
        this.decription = decription;
        this.erd = erd;
        this.uml = uml;
        this.skema = skema;
        this.link = link;
        this.name = name;
        this.trainer = trainer;
    }

    //search project
    public ProjectDTO(Integer idProject, String title, String decription, String skema, String link, List<String> name, String trainer) {
        this.idProject = idProject;
        this.title = title;
        this.decription = decription;
        this.skema = skema;
        this.link = link;
        this.name = name;
        this.trainer = trainer;
    }

}
