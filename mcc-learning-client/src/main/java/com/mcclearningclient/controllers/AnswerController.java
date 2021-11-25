
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.Answer;
import com.mcclearningclient.models.AnswerResponseDTO;
import com.mcclearningclient.models.ResponseFileAnswerDTO;
import com.mcclearningclient.models.ResponseFileDTO;
import com.mcclearningclient.services.AnswerService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    
    //============  trainee -- upload jawaban  ==============
    @PostMapping(value = "/uploadFile")
    public @ResponseBody
    ResponseFileDTO uploadFileAnswer(MultipartFile file, Integer idModule, Date deadline, Date createdAt, 
            Integer idEmployee, Integer idExam ) {
        return answerService.uploadFileAnswer(file, idModule, deadline, createdAt, idEmployee, idExam);
    }
    
    //============ trainer -- Download File Answer by Id  ==============
    @GetMapping(value = "/downloadFile/{idAnswer}")
    public @ResponseBody boolean downloadFile(@PathVariable Integer idAnswer) {
        return answerService.downloadFile(idAnswer);
    }
    
    //============ trainer -- Get Answer Trainee by Exam ==============
    @GetMapping("/file/{idExam}")
    public @ResponseBody List<ResponseFileAnswerDTO> getListFilesAnswer(@PathVariable Integer idExam) {
        return answerService.getListFilesAnswer(idExam);
    }
    
    @GetMapping("/trainee")
    public String getAnswerTrainee() {
        return "/learningClass/answer-trainee";
    }
    //===================================================
    
    //trainee -- update file answer trainee
    @PutMapping ("/answerfile/{id}")
    public @ResponseBody String updateExam(@PathVariable Integer id, MultipartFile file, Integer idEmployee, Integer idExam) {
        return answerService.updateExam(id, file, idEmployee, idExam);
    }
    
//    @GetMapping("/user/answers/{idEmployee}")
//    public List<Answer> getAnswerByIdEmployee(@PathVariable Integer idEmployee) {
//        return answerService.getAnswerByIdEmployee(idEmployee);
//    }
    
    @GetMapping("/user/answers/{idEmployee}")
    public @ResponseBody List<AnswerResponseDTO> getAnswerByIdEmployee(@PathVariable Integer idEmployee) {
        return answerService.getAnswerByIdEmployee(idEmployee);
    }
}
