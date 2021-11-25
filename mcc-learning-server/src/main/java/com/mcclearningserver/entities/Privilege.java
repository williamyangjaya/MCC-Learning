/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LENOVO-KL
 */
@Entity
@Table(name = "privilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p")})
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_privilege")
    private Integer idPrivilege;
    @Basic(optional = false)
    @Column(name = "privilege_name")
    private String privilegeName;
    @ManyToMany(mappedBy = "privilegeList", fetch = FetchType.EAGER)
    private List<Role> roleList;

    public Privilege() {
    }

    public Privilege(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public Privilege(Integer idPrivilege, String privilegeName) {
        this.idPrivilege = idPrivilege;
        this.privilegeName = privilegeName;
    }

    public Integer getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Integer idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    @XmlTransient
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilege != null ? idPrivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idPrivilege == null && other.idPrivilege != null) || (this.idPrivilege != null && !this.idPrivilege.equals(other.idPrivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Privilege[ idPrivilege=" + idPrivilege + " ]";
    }

}
