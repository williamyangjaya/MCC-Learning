/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.AnswerResponseDTO;
import com.mcclearningserver.entities.Answer;
import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Exam;
import com.mcclearningserver.entities.Module;
import com.mcclearningserver.exception.FileStorageException;
import com.mcclearningserver.repositories.AnswerRepository;
import com.mcclearningserver.repositories.ExamRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    ExamRepository examRepository;

    public Answer uploadFile(MultipartFile file, Employee idEmployee, Exam idExam) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Answer answerFile = new Answer(file.getBytes(),
                    fileName, file.getContentType(), idEmployee, idExam);
            answerFile.setCreatedAt(new Date());

            return answerRepository.save(answerFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Answer getFileAnswer(Integer idAnswer) throws FileNotFoundException {
        return answerRepository.findById(idAnswer)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + idAnswer));
    }

    public Stream<Answer> getByIdExam(Integer idExam) {
        Stream<Answer> answers = examRepository.findById(idExam).get().getAnswerList().stream();
        return answers;
    }

    public Answer updateAnswer(Integer idAnswer, MultipartFile file, Employee idEmployee, Exam idExam) throws IOException {
        Answer updateAnswer = answerRepository.findById(idAnswer).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            updateAnswer.setFileName(fileName);
            updateAnswer.setType(file.getContentType());
            updateAnswer.setLink(file.getBytes());
            updateAnswer.setIdEmployee(idEmployee);
            updateAnswer.setIdExam(idExam);
            return answerRepository.save(updateAnswer);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public List<Answer> getAnswerByIdEmployee(Integer id_employee) {
        Employee employeeData = new Employee(id_employee);
        return answerRepository.findByIdEmployee(employeeData); 
    }
}
