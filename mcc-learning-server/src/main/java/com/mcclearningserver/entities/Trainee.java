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
import javax.persistence.ManyToOne;
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
@Table(name = "trainee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trainee.findAll", query = "SELECT t FROM Trainee t")})
public class Trainee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_trainee")
    private Integer idTrainee;
    @Basic(optional = false)
    @Column(name = "batch")
    private String batch;
    @Basic(optional = false)
    @Column(name = "status_mcc")
    private String statusMcc;
    @JoinColumn(name = "id_trainee", referencedColumnName = "id_employee", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @JoinColumn(name = "id_project", referencedColumnName = "id_project")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project idProject;

    public Trainee() {
    }

    public Trainee(Integer idTrainee) {
        this.idTrainee = idTrainee;
    }

    public Trainee(Integer idTrainee, String batch, String statusMcc) {
        this.idTrainee = idTrainee;
        this.batch = batch;
        this.statusMcc = statusMcc;
    }

    public Integer getIdTrainee() {
        return idTrainee;
    }

    public void setIdTrainee(Integer idTrainee) {
        this.idTrainee = idTrainee;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStatusMcc() {
        return statusMcc;
    }

    public void setStatusMcc(String statusMcc) {
        this.statusMcc = statusMcc;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrainee != null ? idTrainee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trainee)) {
            return false;
        }
        Trainee other = (Trainee) object;
        if ((this.idTrainee == null && other.idTrainee != null) || (this.idTrainee != null && !this.idTrainee.equals(other.idTrainee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Trainee[ idTrainee=" + idTrainee + " ]";
    }

}
