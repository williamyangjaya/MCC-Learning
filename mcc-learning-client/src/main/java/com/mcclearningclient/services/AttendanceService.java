/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.Attendance;
import com.mcclearningclient.models.AttendanceByBatchDTO;
import com.mcclearningclient.models.AttendanceDTO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author William Yangjaya
 */
@Service
public class AttendanceService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${api.url}/attendance")
    private String url;

    //get All
    public List<Attendance> listAttendance() {
        ResponseEntity<List<Attendance>> response = restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<Attendance>>() {
                });
        return response.getBody();
    }

    //get All by Id Attendance
    public Attendance attendanceById(Integer id) {
        return restTemplate.getForEntity(url + "/" + id, Attendance.class).getBody();
    }

    //get All by Batch and Date
    public List<Attendance> attendanceByBatch(AttendanceByBatchDTO attendanceByBatchDTO) {
        HttpEntity entity = new HttpEntity(attendanceByBatchDTO, RequestFormat.createHeaders());
        ResponseEntity<List<Attendance>> res = restTemplate.exchange(url + "/getAttendance", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Attendance>>() {
        });
        return res.getBody();
    }

    //get All by Employee Id
    public List<Attendance> attendanceByEmpId(Integer idEmployee) {
        ResponseEntity<List<Attendance>> res = restTemplate.exchange(url + "/get/" + idEmployee, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()), new ParameterizedTypeReference<List<Attendance>>() {
        });
        return res.getBody();
    }

    //export by id and Date with DTO
    public boolean exportAllAttendance(AttendanceDTO attendanceDTO) {
        HttpEntity entity = new HttpEntity(attendanceDTO, RequestFormat.createHeaders());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            ResponseEntity<byte[]> response = restTemplateBuilder.build()
                    .exchange(url + "/downloadExcelFile", HttpMethod.POST, entity, byte[].class);
            Files.write(Paths.get("D:\\Data\\Downloads\\attendance.xlsx"), response.getBody());
            System.out.println(response.getBody());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //get All by date 
    public Attendance[] getByDate(String date) {
        ResponseEntity<Attendance[]> res = restTemplate.getForEntity(url + "/" + date, Attendance[].class);
        Attendance[] attendance = res.getBody();
        return attendance;
    }

    //create attendance
    public Attendance insertAttendance(Attendance attendance) {
        HttpEntity entity = new HttpEntity(attendance, RequestFormat.createHeaders());
        ResponseEntity<Attendance> res = restTemplate.exchange(url, HttpMethod.POST, entity, Attendance.class);
        return res.getBody();
    }

    //update all data attendance
    public Attendance update(Integer id, Attendance attendance) {
        HttpEntity entity = new HttpEntity(attendance, RequestFormat.createHeaders());
        ResponseEntity<Attendance> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, Attendance.class);
        return res.getBody();
    }

    //update verify for trainer
    public Attendance updateVerify(Integer id, boolean isVerified) {
        HttpEntity entity = new HttpEntity(isVerified, RequestFormat.createHeaders());
        ResponseEntity<Attendance> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, Attendance.class);
        return res.getBody();
    }

}
