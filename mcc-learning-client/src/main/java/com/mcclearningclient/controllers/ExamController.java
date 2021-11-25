/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.ResponseFileDTO;
import com.mcclearningclient.services.ExamService;
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
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    ExamService examService;
    
    // ============== Trainee -- View Information exam =======
    @GetMapping("/trainee")
    public String viewInformation() {
        return "learningClass/exam-trainee";
    }
    
    // ============== Trainer -- View Information exam =======
    @GetMapping("/trainer")
    public String getAllExam() {
        return "learningClass/exam-trainer";
    }
    
    //============ trainer -- upload File Exam  ==============
    @PostMapping(value = "/uploadFile")
    public @ResponseBody
    ResponseFileDTO uploadFile(MultipartFile file, Integer idModule, Date deadline, boolean isActive) {
        return examService.uploadFileExam(file, idModule, deadline, isActive);
    }
    
    //============ trainee -- Download File Exam by Id  ==============
    @GetMapping(value = "/downloadFile/{idExam}")
    public @ResponseBody boolean downloadFile(@PathVariable Integer idExam) {
        return examService.downloadFile(idExam);
    }
    
    //============ trainee -- getAllFile exam  ==============
    @GetMapping("/files")
    public @ResponseBody List<ResponseFileDTO> getListFile() {
        return examService.getListFile();
    }
    
    @GetMapping("{idExam}")
    public @ResponseBody ResponseFileDTO getExamById(@PathVariable Integer idExam) {
        return examService.getExamById(idExam);
    }
    
    @GetMapping("/files/{idModule}")
    public @ResponseBody List<ResponseFileDTO> getFileByIdModule(@PathVariable Integer idModule) {
        return examService.getExamByIdModule(idModule);
    }
    
    //=========== trainee -- update is Active ==========
    @PutMapping ("/active/{id}")
    public @ResponseBody String updateActive(@PathVariable Integer id, boolean isActive) {
        return examService.updateActive(id, isActive);
    }
    
    //========== trainer -- update file exam ============
    @PutMapping ("/updateFile/{idExam}")
    public @ResponseBody String updateExam(@PathVariable Integer idExam, MultipartFile file, Date deadline) {
        return examService.updateExam(idExam, file, deadline);
    }
    
}
