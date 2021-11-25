/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import com.mcclearningserver.entities.Trainee;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProjectDTO {
    private Integer idProject;
    private String title;
    private String decription;
    private String erd;
    private String uml;
    private String skema;
    private String link;
    private List<Trainee> trainee;
    private List<String> name;
    private String trainer;
    private String batch;

    public ProjectDTO() {
    }

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

    public ProjectDTO(Integer idProject, String title, String decription, String erd, String uml, String skema, String link, List<String> name, String trainer, String batch) {
        this.idProject = idProject;
        this.title = title;
        this.decription = decription;
        this.erd = erd;
        this.uml = uml;
        this.skema = skema;
        this.link = link;
        this.name = name;
        this.trainer = trainer;
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
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

    public List<Trainee> getTrainee() {
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
    
    
}
