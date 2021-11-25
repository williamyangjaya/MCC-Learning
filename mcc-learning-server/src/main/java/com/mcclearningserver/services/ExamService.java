/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.entities.Exam;
import com.mcclearningserver.entities.Module;
import com.mcclearningserver.exception.FileStorageException;
import com.mcclearningserver.repositories.ExamRepository;
import com.mcclearningserver.repositories.ModuleRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO-KL
 */
@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ModuleService moduleService;

    public Exam uploadFile(MultipartFile file, Exam exam, Integer idModule) {
        Module module = moduleService.getModuleFile(idModule);
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Exam dbFile = new Exam();
            dbFile.setIdExam(idModule);
            dbFile.setLink(file.getBytes());
            dbFile.setFileName(fileName);
            dbFile.setDeadline(exam.getDeadline());
            dbFile.setType(file.getContentType());
            dbFile.setIsActive(exam.getIsActive());
            dbFile.setModule(module);
            dbFile.setCreatedAt(new Date());
            return examRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Exam getFileExam(Integer idExam) throws FileNotFoundException {
        return examRepository.findById(idExam)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + idExam));
    }

    public Stream<Exam> getAllFiles() {
        return examRepository.findAll().stream();
    }


    public String updateActive(Integer idExam, boolean isActive) {
        Exam exam = examRepository.findById(idExam).get();
        exam.setIsActive(isActive);
        examRepository.save(exam);
        return "siap";
    }

    public Exam updateExam(Integer idExam, MultipartFile file, Date deadline) throws IOException {
        Exam updateExam = examRepository.findById(idExam).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            updateExam.setFileName(fileName);
            updateExam.setType(file.getContentType());
            updateExam.setLink(file.getBytes());
            updateExam.setDeadline(deadline);
            return examRepository.save(updateExam);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
