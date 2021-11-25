/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO-KL
 */
@Entity
@Table(name = "attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_attendance")
    private Integer idAttendance;
    @Basic(optional = false)
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Lob
    @Basic(optional = true)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @Column(name = "is_verified")
    private boolean isVerified;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee idEmployee;

    public Attendance() {
    }

    public Attendance(Date date) {
        this.date = date;
    }

    public Attendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Attendance(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Attendance(Integer idAttendance, Date date, Date createdAt, String status, boolean isVerified) {
        this.idAttendance = idAttendance;
        this.date = date;
        this.createdAt = createdAt;
        this.status = status;
        this.isVerified = isVerified;
    }

    public Attendance(Integer idAttendance, Date date, Date createdAt, String status, String note, boolean isVerified, Employee idEmployee) {
        this.idAttendance = idAttendance;
        this.date = date;
        this.createdAt = createdAt;
        this.status = status;
        this.note = note;
        this.isVerified = isVerified;
        this.idEmployee = idEmployee;
    }

    public Integer getIdAttendance() {
        return idAttendance;
    }

    public void setIdAttendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttendance != null ? idAttendance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.idAttendance == null && other.idAttendance != null) || (this.idAttendance != null && !this.idAttendance.equals(other.idAttendance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mcclearningserver.entities.Attendance[ idAttendance=" + idAttendance + " ]";
    }

}
