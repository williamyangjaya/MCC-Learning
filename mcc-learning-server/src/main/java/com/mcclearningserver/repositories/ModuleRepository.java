/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.repositories;

import com.mcclearningserver.entities.Module;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
    List<Module> findByIdClass(Integer idClass);
    List<Module> findByCategory(String category);
    List<Module> findByIdClassAndCategory(Integer idClass, String category);
}
