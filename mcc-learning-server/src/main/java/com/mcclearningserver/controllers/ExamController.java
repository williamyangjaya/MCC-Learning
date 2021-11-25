
package com.mcclearningserver.controllers;

import com.mcclearningserver.entities.Exam;
import com.mcclearningserver.message.ResponseFile;
import com.mcclearningserver.repositories.ExamRepository;
import com.mcclearningserver.services.AnswerService;
import com.mcclearningserver.services.ExamService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;
    
    @Autowired
    AnswerService answerService;

    @Autowired
    ExamRepository examRepository;

    //trainer -- upload File Exam || DONE
    @PostMapping("/uploadFile") 
    public ResponseFile uploadFileExam(
            @RequestParam("file") MultipartFile file, 
            Integer idModule, Exam exam
    ) {
        Exam fileName = examService.uploadFile(file, exam, idModule);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/exam/downloadFile/")
                .path(fileName.getIdExam().toString())
                .toUriString();
        return new ResponseFile(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(),fileName.getDeadline(), fileName.getCreatedAt());
    }

    //trainee -- Download File Exam by Id || DONE
    @GetMapping("/downloadFile/{idExam}")
    public ResponseEntity< Resource> downloadFile(@PathVariable Integer idExam, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        Exam examFile = examService.getFileExam(idExam);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(examFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + examFile.getFileName() + "\"")
                .body(new ByteArrayResource(examFile.getLink()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFile> getExamById(@PathVariable Integer id) throws FileNotFoundException {
        Exam files = examService.getFileExam(id);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/exam/downloadFile/")
                .path(files.getIdExam().toString())
                .toUriString();
        
        ResponseFile response = new ResponseFile(
                files.getFileName(), 
                fileDownloadUri, 
                files.getType(), 
                files.getLink().length, 
                files.getDeadline(), 
                files.getCreatedAt());
        
        return ResponseEntity.ok().body(response);
    }

    //find All response file
    //trainee -- getAllFile exam || DONE
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = examService.getAllFiles().map(examFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/exam/downloadFile/")
                    .path(examFile.getIdExam().toString())
                    .toUriString();

            return new ResponseFile(
                    examFile.getFileName(), //nama
                    fileDownloadUri, //url donlot
                    examFile.getType(), 
                    examFile.getLink().length,
            examFile.getDeadline(),//deadline
            examFile.getCreatedAt()); //created at
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    //trainee -- update is Active ||DONE
    @PutMapping("/active/{id}")
    public String updateActive(@PathVariable Integer id, @RequestParam boolean isActive) throws MessagingException {
        examService.updateActive(id, isActive);
        return "success";
    }

    //trainer -- update file exam || DONE
    @PutMapping("/examfile/{idExam}")
    public String updateExam(@PathVariable Integer idExam, @RequestParam MultipartFile file, @DateTimeFormat(pattern = "dd/MM/yyyy") Date deadline) throws MessagingException, IOException {
        examService.updateExam(idExam, file, deadline);
        return "success";
    }

}
