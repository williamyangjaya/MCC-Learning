/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author ROG
 */
@Data
public class FeedbackForm {

    private Integer idForm;
    private String link;
    private String category;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private Date createdAt;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private Date updatedAt;

    public FeedbackForm() {
    }

    public FeedbackForm(Integer idForm) {
        this.idForm = idForm;
    }

    public FeedbackForm(Integer idForm, String link, String category) {
        this.idForm = idForm;
        this.link = link;
        this.category = category;
    }

}
