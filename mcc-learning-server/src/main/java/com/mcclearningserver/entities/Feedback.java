/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO-KL
 */
@Entity
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_form")
    private Integer idForm;
    @Basic(optional = false)
    @Lob
    @Column(name = "link")
    private String link;
    @Basic(optional = false)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    @Column(name = "created_at")
    private Date createdAt;
    @Basic(optional = true)
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    @Column(name = "updated_at")
    private Date updatedAt;

    public Feedback() {
    }

    public Feedback(Integer idForm) {
        this.idForm = idForm;
    }

    public Feedback(Integer idForm, String link, String category) {
        this.idForm = idForm;
        this.link = link;
        this.category = category;
    }

    public Integer getIdForm() {
        return idForm;
    }

    public void setIdForm(Integer idForm) {
        this.idForm = idForm;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForm != null ? idForm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.idForm == null && other.idForm != null) || (this.idForm != null && !this.idForm.equals(other.idForm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Feedback[ idForm=" + idForm + " ]";
    }
    
    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }
    
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
}
