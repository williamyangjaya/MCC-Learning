/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "project_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectFile.findAll", query = "SELECT p FROM ProjectFile p")})
public class ProjectFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_file")
    private Integer idFile;
    @Lob
    @Column(name = "erd_file")
    private byte[] erdFile;
    @JoinColumn(name = "id_file", referencedColumnName = "id_project", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Project project;

    public ProjectFile() {
    }

    public ProjectFile(Integer idFile) {
        this.idFile = idFile;
    }

    public ProjectFile(byte[] erdFile) {
        this.erdFile = erdFile;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public byte[] getErdFile() {
        return erdFile;
    }

    public void setErdFile(byte[] erdFile) {
        this.erdFile = erdFile;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFile != null ? idFile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectFile)) {
            return false;
        }
        ProjectFile other = (ProjectFile) object;
        if ((this.idFile == null && other.idFile != null) || (this.idFile != null && !this.idFile.equals(other.idFile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.ProjectFile[ idFile=" + idFile + " ]";
    }
    
}
