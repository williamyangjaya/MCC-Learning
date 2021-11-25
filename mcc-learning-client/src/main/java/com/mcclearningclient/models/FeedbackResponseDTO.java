/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author ROG
 */
@Data
public class FeedbackResponseDTO {
    
    private Integer idForm;
    private String link;
    private String category;
    private Date createdAt;
    private Date updatedAt;

    public FeedbackResponseDTO() {
    }

    public FeedbackResponseDTO(Integer idForm, String link, String category, Date createdAt, Date updatedAt) {
        this.idForm = idForm;
        this.link = link;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
     

            
    
    
    
    
}
