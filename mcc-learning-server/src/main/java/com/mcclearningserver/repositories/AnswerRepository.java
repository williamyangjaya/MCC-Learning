/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.repositories;

import com.mcclearningserver.entities.Answer;
import com.mcclearningserver.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LENOVO-KL
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByIdEmployee(Employee employeeId);
}
