/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;


/**
 *
 * @author William Yangjaya
 */
public class Employee {

    private Integer idEmployee;
    private String name;
    private String email;
    private String position;
    private Employee idTrainer;
    private Class idClass;

    public Employee() {
    }

    public Employee(Integer idEmployee, String name, String email, String position, Employee idTrainer, Class idClass) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.email = email;
        this.position = position;
        this.idTrainer = idTrainer;
        this.idClass = idClass;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(Employee idTrainer) {
        this.idTrainer = idTrainer;
    }

    public Class getIdClass() {
        return idClass;
    }

    public void setIdClass(Class idClass) {
        this.idClass = idClass;
    }

}
