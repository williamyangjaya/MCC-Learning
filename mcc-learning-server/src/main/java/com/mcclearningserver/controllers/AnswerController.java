
package com.mcclearningserver.controllers;

import com.mcclearningserver.entities.Answer;
import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Exam;
import com.mcclearningserver.message.ResponseFile;
import com.mcclearningserver.message.ResponseFileAnswer;
import com.mcclearningserver.services.AnswerService;
import com.mcclearningserver.services.ExamService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;
    
    @Autowired
    ExamService examService;
    
    
    //trainee -- upload jawaban
    @PostMapping("/uploadFile")
    public ResponseFile uploadFileAnswer(@RequestParam("file") MultipartFile file, Employee idEmployee, Exam idExam) {
        Answer fileName = answerService.uploadFile(file, idEmployee, idExam);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("answer/downloadFile/")
                .path(fileName.getIdExam().getIdExam().toString())
                .toUriString();
        return new ResponseFile(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    //trainer -- Download File Answer by Id 
    @GetMapping("/downloadFile/{idAnswer}")
    public ResponseEntity< Resource> downloadFile(@PathVariable Integer idAnswer, HttpServletRequest request) throws FileNotFoundException {
        Answer answerFile = answerService.getFileAnswer(idAnswer);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(answerFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + answerFile.getFileName() + "\"")
                .body(new ByteArrayResource(answerFile.getLink()));
    }
    
    //find All response file     
    //the trainer can know who has sent the answer
    @GetMapping("/files/{idExam}")
    public ResponseEntity<List<ResponseFileAnswer>> getListFilesAnswer(@PathVariable Integer idExam) {
        List<ResponseFileAnswer> files = answerService.getByIdExam(idExam).map(answerFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/answer/downloadFile/")
                    .path(answerFile.getIdAnswer().toString())
                    .toUriString();

            return new ResponseFileAnswer(
                    answerFile.getFileName(), //name
                    fileDownloadUri, //link download answer
                    answerFile.getType(), 
                    answerFile.getLink().length,
            answerFile.getIdEmployee().getIdEmployee(),
            answerFile.getIdExam().getFileName(),
            answerFile.getIdExam().getDeadline(),
            answerFile.getCreatedAt());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
    //trainee -- update file answer trainee 
    @PutMapping("/answerfile/{id}")
    public String updateExam(@PathVariable Integer id, @RequestParam MultipartFile file, Employee idEmployee, Exam idExam) throws MessagingException, IOException {
        answerService.updateAnswer(id, file, idEmployee, idExam);
        return "success";
    }
    
    @GetMapping("/user/answers/{idEmployee}")
    public List<Answer> getAnswerByIdEmployee(@PathVariable Integer idEmployee) {
        return answerService.getAnswerByIdEmployee(idEmployee);
    }
    
    

}
