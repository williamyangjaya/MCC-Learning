/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import java.util.Date;

/**
 *
 * @author ROG
 */
public class FeedbackDTO {
    private Integer id_form;
    private String link;
    private String category;
    private Date created_at;
    private Date updated_at;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Integer id_form, String link, String category, Date created_at, Date updated_at) {
        this.id_form = id_form;
        this.link = link;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId_form() {
        return id_form;
    }

    public void setId_form(Integer id_form) {
        this.id_form = id_form;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    
    
    
}
