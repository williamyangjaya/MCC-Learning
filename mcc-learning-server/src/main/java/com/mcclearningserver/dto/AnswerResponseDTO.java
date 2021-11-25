/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.dto;

import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Exam;
import java.util.Date;

/**
 *
 * @author ROG
 */
public class AnswerResponseDTO {
    private Integer id_answer;
    private byte[] link;
    private Date created_at;
    private String filename;
    private String type;
    private Employee employee;
    private Exam exam;

    public AnswerResponseDTO() {
    }

    public AnswerResponseDTO(Integer id_answer, byte[] link, Date created_at, String filename, String type, Employee employee, Exam exam) {
        this.id_answer = id_answer;
        this.link = link;
        this.created_at = created_at;
        this.filename = filename;
        this.type = type;
        this.employee = employee;
        this.exam = exam;
    }

    public Integer getId_answer() {
        return id_answer;
    }

    public void setId_answer(Integer id_answer) {
        this.id_answer = id_answer;
    }

    public byte[] getLink() {
        return link;
    }

    public void setLink(byte[] link) {
        this.link = link;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    
    
    
}
