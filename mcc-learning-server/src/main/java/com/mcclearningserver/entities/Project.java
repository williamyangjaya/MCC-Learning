/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@JsonIgnoreProperties("hibernateLazyInitializer")
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")})
public class Project implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
    private ProjectFile projectFile;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_project")
    private Integer idProject;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "erd")
    private String erd;
    @Lob
    @Column(name = "uml")
    private String uml;
    @Lob
    @Column(name = "skema")
    private String skema;
    @Lob
    @Column(name = "link")
    private String link;
    @JoinColumn(name = "current_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Status currentStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<History> historyList;
    @OneToMany(mappedBy = "idProject", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Trainee> traineeList;

    public Project() {
    }

    public Project(Integer idProject) {
        this.idProject = idProject;
    }

    public Project(Integer idProject, String title, String description, String erd, String uml, String schema, String link, Status currentStatus) {
        this.idProject = idProject;
        this.title = title;
        this.description = description;
        this.erd = erd;
        this.uml = uml;
        this.skema = schema;
        this.link = link;
        this.currentStatus = currentStatus;
    }

    public Project(Integer idProject, String title, String description) {
        this.idProject = idProject;
        this.title = title;
        this.description = description;
    }

    public Project(String title, String description, Status currentStatus) {
        this.title = title;
        this.description = description;
        this.currentStatus = currentStatus;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErd() {
        return erd;
    }

    public void setErd(String erd) {
        this.erd = erd;
    }

    public String getUml() {
        return uml;
    }

    public void setUml(String uml) {
        this.uml = uml;
    }

    public String getSchema() {
        return skema;
    }

    public void setSchema(String schema) {
        this.skema = schema;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    @XmlTransient
    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    @XmlTransient
    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.idProject == null && other.idProject != null) || (this.idProject != null && !this.idProject.equals(other.idProject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Project[ idProject=" + idProject + " ]";
    }

    public ProjectFile getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(ProjectFile projectFile) {
        this.projectFile = projectFile;
    }

}
