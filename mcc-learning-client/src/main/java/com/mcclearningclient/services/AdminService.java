/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.AllProfileDTO;
import com.mcclearningclient.models.ProfileDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class AdminService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}/admin")
    private String url;
    
    //GET ALL TRAINER
    public List<ProfileDTO> getAllTrainerProfile() {
        ResponseEntity<List<ProfileDTO>> response = restTemplate
                .exchange(url + "/trainer", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ProfileDTO>>() {
                });
        return response.getBody();
    }
    
    //GET ALL TRAINEE
    public List<AllProfileDTO> getAllTraineeProfile() {
        ResponseEntity<List<AllProfileDTO>> response = restTemplate
                .exchange(url + "/trainee", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<AllProfileDTO>>() {
                });
        return response.getBody();
    }
    
    //GET ALL ALUMNI
    public List<AllProfileDTO> getAllAlumniProfile() {
        ResponseEntity<List<AllProfileDTO>> response = restTemplate
                .exchange(url + "/alumni", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<AllProfileDTO>>() {
                });
        return response.getBody();
    }
}
