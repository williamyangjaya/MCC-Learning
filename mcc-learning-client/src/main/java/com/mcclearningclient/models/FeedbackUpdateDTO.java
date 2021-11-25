/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import javafx.scene.text.Text;
import lombok.Data;

/**
 *
 * @author ROG
 */

@Data
public class FeedbackUpdateDTO {
    
    private Text link;

    public FeedbackUpdateDTO() {
    }

    public FeedbackUpdateDTO(Text link) {
        this.link = link;
    }
    
}
