/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author ROG
 */
@Data
@Builder
public class ExamFileDTO {
    private String filename;
    private byte[] linkExam;
    private byte[] linkAnswer;
    private Date deadline;
    private Date uploadedAt;

    public ExamFileDTO() {
    }

    public ExamFileDTO(String filename, byte[] linkExam, byte[] linkAnswer, Date deadline, Date uploadedAt) {
        this.filename = filename;
        this.linkExam = linkExam;
        this.linkAnswer = linkAnswer;
        this.deadline = deadline;
        this.uploadedAt = uploadedAt;
    }
    
    
    
    
}
