package com.mcclearningclient.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.Answer;
import com.mcclearningclient.models.AnswerResponseDTO;
import com.mcclearningclient.models.Employee;
import com.mcclearningclient.models.ResponseFileAnswerDTO;
import com.mcclearningclient.models.ResponseFileDTO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class AnswerService {

    @Autowired
    private RestTemplate restTemplate;
    

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${api.url}/answer")
    private String url;

    //trainee -- upload jawaban
    public ResponseFileDTO uploadFileAnswer(MultipartFile file, Integer idModule, Date deadline, Date createdAt,
            Integer idEmployee, Integer idExam) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<ResponseFileDTO> res = restTemplate.exchange(url + "/uploadFile", HttpMethod.POST, entity,
                new ParameterizedTypeReference<ResponseFileDTO>() {
        });
        return res.getBody();
    }

    //trainer -- Download File Answer by Id 
    public boolean downloadFile(Integer idAnswer) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            ResponseEntity<byte[]> response = restTemplateBuilder.build()
                    .exchange(url + "/downloadFile/" + idAnswer, HttpMethod.POST, entity, byte[].class);
            Files.write(Paths.get("D:\\Data\\Downloads\\answer.xlsx"), response.getBody());
            System.out.println(response.getBody());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find All response file     
    //the trainer can know who has sent the answer
    public List<ResponseFileAnswerDTO> getListFilesAnswer(Integer idExam) {
        ResponseEntity<List<ResponseFileAnswerDTO>> res = restTemplate
                .exchange(url + "/files/" + idExam, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ResponseFileAnswerDTO>>() {
                });
        return res.getBody();
    }

    //trainee -- update file answer trainee
    public String updateExam(Integer id, MultipartFile file, Integer idEmployee, Integer idExam) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<String> res = restTemplate.exchange(url + "/answerfile/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<String>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

    //trainee Get their Answer
//    public List<Answer> getAnswerByIdEmployee(Integer idEmployee) {
//        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
//        ResponseEntity<List<Answer>> res = restTemplate.exchange(url + "/user/answers/" + idEmployee, HttpMethod.GET, entity,
//                new ParameterizedTypeReference<List<Answer>>() {
//        });
//        return res.getBody();
//    }
    
    public List<AnswerResponseDTO> getAnswerByIdEmployee(Integer idEmployee) {
        ResponseEntity<List<AnswerResponseDTO>> res = restTemplate
                .exchange(url + "/user/answers/" + idEmployee, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<AnswerResponseDTO>>() {
                });
                
        return res.getBody();
    }
}
