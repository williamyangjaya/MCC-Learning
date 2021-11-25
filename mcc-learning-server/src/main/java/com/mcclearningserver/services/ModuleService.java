/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.ClassResponseDTO;
import com.mcclearningserver.entities.MccClass;
import com.mcclearningserver.entities.Module;
import com.mcclearningserver.exception.FileStorageException;
import com.mcclearningserver.exception.MyFileNotFoundException;
import com.mcclearningserver.repositories.ClassRepository;
import com.mcclearningserver.repositories.ModuleRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ClassRepository classRepository;

    public List<Module> getByIdClass(Integer idClass) {
        return moduleRepository.findByIdClass(idClass);
    }

    public List<Module> getByNameClass(String nameClass) {
        Integer idClass = classRepository.findByName(nameClass).getIdClass();
        return moduleRepository.findByIdClass(idClass);
    }

    public List<Module> getByIdClassAndCategory(Integer idClass, String category) {
        return moduleRepository.findByIdClassAndCategory(idClass, category);
    }

    public Module storeModule(Module module, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            module.setFile(file.getBytes());
            module.setUpdatedAt(new Date());
            module.setFileType(file.getContentType());

            return moduleRepository.save(module);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Module getModuleFile(Integer idModule) {
        return moduleRepository.findById(idModule)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + idModule));
    }
    
    

    public Module updateModuleById(Integer id, Module moduleNew, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Module module = moduleRepository.findById(id).get();

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            module.setFile(file.getBytes());
            module.setTitle(moduleNew.getTitle());
            module.setCategory(moduleNew.getCategory());
            module.setIdClass(moduleNew.getIdClass());

            return moduleRepository.save(module);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<ClassResponseDTO> getClassList() {
        List<MccClass> getClass = classRepository.findAll();
        List<ClassResponseDTO> resList = new ArrayList<>();
        for (MccClass c : getClass) {
            ClassResponseDTO classres = new ClassResponseDTO(
                    c.getIdClass(),
                    c.getName());
            resList.add(classres);
        }
        System.out.println(getClass);
        return resList;
    }
}
