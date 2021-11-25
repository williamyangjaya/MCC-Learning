/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO-KL
 */
@Entity
@Table(name = "class")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM MccClass c")})
public class MccClass implements Serializable {

    @OneToMany(mappedBy = "idClass", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Employee> employeeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_class")
    private Integer idClass;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public MccClass() {
    }

    public MccClass(Integer idClass) {
        this.idClass = idClass;
    }

    public MccClass(Integer idClass, String name) {
        this.idClass = idClass;
        this.name = name;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClass != null ? idClass.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MccClass)) {
            return false;
        }
        MccClass other = (MccClass) object;
        if ((this.idClass == null && other.idClass != null) || (this.idClass != null && !this.idClass.equals(other.idClass))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Class[ idClass=" + idClass + " ]";
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
