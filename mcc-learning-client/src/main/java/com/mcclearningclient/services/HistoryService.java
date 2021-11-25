/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.HistoryDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HistoryService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}/history")
    private String url;
    
    
//    public List<HistoryDTO> getHistoryByIdMcc(Integer Id) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        AuthResponse a = new AuthResponse(auth.getName());
//        HttpEntity entity = new HttpEntity(a, RequestFormat.createHeaders());
//        ResponseEntity<List<HistoryDTO>> response = restTemplate
//                .exchange(url + "/employee", HttpMethod.GET,
//                        entity,
//                        new ParameterizedTypeReference<List<HistoryDTO>>() {
//                });
//        
//        return response.getBody();
//    }
    
//    @GetMapping("/employee/{id}")
//    public List<HistoryDTO> getHistoryByIdMcc(@PathVariable Integer id){
//        System.out.println("cetak auth history");
//        return historyService.getHistoryByIdMcc(id);
//    }
//    
//     public ProjectDTO getFullProjectByIdTrainee(Integer id) {
//        ResponseEntity<ProjectDTO> res = restTemplate
//                .exchange(url + "/myProject/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
//                        new ParameterizedTypeReference<ProjectDTO>() {
//                });
//        return res.getBody();
    
    public List<HistoryDTO> getHistoryByIdMcc(Integer id) {
        ResponseEntity<List<HistoryDTO>> res = restTemplate
                .exchange(url + "/employee/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<HistoryDTO>>() {
                });
        return res.getBody();
    }
    
    
    public List<HistoryDTO> getAllHistory() {
        ResponseEntity<List<HistoryDTO>> response = restTemplate
                .exchange(url + "/get-all", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<HistoryDTO>>() {
                });
        System.out.println(response.getBody());
        return response.getBody();
    }
}