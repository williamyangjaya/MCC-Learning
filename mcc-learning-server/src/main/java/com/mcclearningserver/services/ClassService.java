/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.ClassResponseDTO;
import com.mcclearningserver.entities.MccClass;
import com.mcclearningserver.repositories.ClassRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ROG
 */

@Service
public class ClassService {
    
    @Autowired
    ClassRepository classRepository;
    
    public Optional<MccClass> getClassById(Integer id) {
        return classRepository.findById(id);
    }
}