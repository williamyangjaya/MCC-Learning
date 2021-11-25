package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.AnswerResponseDTO;
import com.mcclearningclient.models.ExamFileDTO;
import com.mcclearningclient.models.ModuleDTO;
import com.mcclearningclient.models.ResponseFileDTO;
import com.mcclearningclient.models.UserQuizDto;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExamService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${api.url}/exam")
    private String url;

    //trainer -- upload File Exam 
    public ResponseFileDTO uploadFileExam(MultipartFile file, Integer idModule, Date deadline, boolean isActive) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<ResponseFileDTO> res = restTemplate.exchange(url + "/uploadFile", HttpMethod.POST, entity,
                new ParameterizedTypeReference<ResponseFileDTO>() {
        });
        return res.getBody();
    }

    //trainee -- Download File Exam by Id 
    public boolean downloadFile(Integer idExam) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            ResponseEntity<byte[]> response = restTemplateBuilder.build()
                    .exchange(url + "/downloadFile/" + idExam, HttpMethod.POST, entity, byte[].class);
            Files.write(Paths.get("D:\\Data\\Downloads\\exam.xlsx"), response.getBody());
            System.out.println(response.getBody());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //trainee -- getAllFile exam
    public List<ResponseFileDTO> getListFile() {
        ResponseEntity<List<ResponseFileDTO>> res = restTemplate
                .exchange(url + "/files", HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ResponseFileDTO>>() {
                });
        return res.getBody();
    }

    public ResponseFileDTO getExamById(Integer idExam) {
        ResponseEntity<ResponseFileDTO> res = restTemplate
                .exchange(url + "/" + idExam, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<ResponseFileDTO>() {
                });
        return res.getBody();
    }

    public List<ResponseFileDTO> getExamByIdModule(Integer idModule) {
        ResponseEntity<List<ResponseFileDTO>> res = restTemplate
                .exchange(url + "/files/" + idModule, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ResponseFileDTO>>() {
                });
        return res.getBody();
    }

    //trainee -- update is Active
    public String updateActive(Integer id, boolean isActive) {
        HttpEntity entity = new HttpEntity(isActive, RequestFormat.createHeaders());
        ResponseEntity<String> res = restTemplate.exchange(url + "/active/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<String>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

    //trainer -- update file exam 
    public String updateExam(Integer idExam, MultipartFile file, Date deadline) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<String> res = restTemplate.exchange(url + "/examfile/" + idExam, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<String>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

//    public List<ExamFileDTO> getUserQuiz(UserQuizDto request) {
//
//        List<ResponseFileDTO> userExam = getExamByIdModule(request.getIdModule());
//
//        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
//
//        //Hit Get Modules by idClass and Category
//        ResponseEntity<List<ModuleDTO>> module = restTemplate.exchange(url + "/" + request.getIdClass() + "/" + request.getCategory(), HttpMethod.GET, entity,
//                new ParameterizedTypeReference<List<ModuleDTO>>() {
//        });
//
//        //Hit Get Answer By id Employee
//        ResponseEntity<List<AnswerResponseDTO>> answer = restTemplate.exchange("http://localhost:8082/user/answers/" + request.getIdEmployee(), HttpMethod.GET, entity,
//                new ParameterizedTypeReference<List<AnswerResponseDTO>>() {
//        });
//        
//        List<ModuleDTO> modules = new ArrayList<>();
//        for(ModuleDTO moduleList : module.getBody()) {
//            ModuleDTO temp = ModuleDTO
//                .builder()
//                .idModule(moduleList.getIdModule())
//                .title(moduleList.getTitle())
//                .file(moduleList.getFile())
//                .category(moduleList.getCategory())
//                .createdAt(moduleList.getCreatedAt())
//                .updatedAt(moduleList.getUpdatedAt())
//                .build();
//            
//            modules.add(temp);
//        }
//        
//        List<AnswerResponseDTO> answers = new ArrayList<>();
//        for(AnswerResponseDTO answerList : answer.getBody()) {
//            AnswerResponseDTO temp = AnswerResponseDTO
//                    .builder()
//                    .idAnswer(answerList.getIdAnswer())
//                    .fileName(answerList.getFileName())
//                    .link(answerList.getLink())
//                    .createdAt(answerList.getCreatedAt())
//                    .type(answerList.getType())
//                    .idEmployee(answerList.getIdEmployee())
//                    .build();
//            
//            answers.add(temp);
//                    
//        }
//
//        List<ExamFileDTO> examTrainee = new ArrayList<>();
//        for()
//    }
}
