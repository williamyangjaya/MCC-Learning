/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.repositories;

import com.mcclearningserver.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO-KL
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
