/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.List;
import lombok.Data;

@Data
public class SearchProject {

    private Integer idProject;
    private String title;
    private List<String> name;
    private String trainer;
    private String description;
    private String skema;
    private String link;

    public SearchProject() {
    }

    public SearchProject(Integer idProject, String title, List<String> name, String trainer, String description, String skema, String link) {
        this.idProject = idProject;
        this.title = title;
        this.name = name;
        this.trainer = trainer;
        this.description = description;
        this.skema = skema;
        this.link = link;
    }

}
