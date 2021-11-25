/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Arnum Sk
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_exam")
    private Integer idExam;
    @Basic(optional = false)
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Lob
    @Column(name = "link")
    private byte[] link;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Basic(optional = false)
    @Column(name = "deadline")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy")
    private Date deadline;
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @OneToOne(targetEntity = Module.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_exam", referencedColumnName = "id_module")
    protected Module module;
    @OneToMany(mappedBy = "idExam", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Answer> answerList;

    public Exam() {
    }

    public Exam(Integer idExam) {
        this.idExam = idExam;
    }

    public Exam(Integer idExam, String fileName, String type, byte[] link, Date deadline, boolean isActive) {
        this.idExam = idExam;
        this.fileName = fileName;
        this.type = type;
        this.link = link;
        this.deadline = deadline;
        this.isActive = isActive;
    }

    public Exam(Integer idExam, String fileName, String type, byte[] link, Date deadline, boolean isActive, Date createdAt) {
        this.idExam = idExam;
        this.fileName = fileName;
        this.type = type;
        this.link = link;
        this.deadline = deadline;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public Integer getIdExam() {
        return idExam;
    }

    public void setIdExam(Integer idExam) {
        this.idExam = idExam;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getLink() {
        return link;
    }

    public void setLink(byte[] link) {
        this.link = link;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAttendanceList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExam != null ? idExam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.idExam == null && other.idExam != null) || (this.idExam != null && !this.idExam.equals(other.idExam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Exam[ idExam=" + idExam + " ]";
    }

}
