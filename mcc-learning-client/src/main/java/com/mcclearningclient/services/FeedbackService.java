/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.FeedbackForm;
import com.mcclearningclient.models.FeedbackUpdateDTO;
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
 * @author ROG
 */
@Service
public class FeedbackService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url}/feedback-form")
    private String url;

    //Get Feedback
    public List<FeedbackForm> getFeedback() {
        ResponseEntity<List<FeedbackForm>> response = restTemplate
                .exchange(url, HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<FeedbackForm>>() {
                });
        System.out.println("================  Feedback Project  ================");
        return response.getBody();
    }

    //Get Feedback By Category
    public FeedbackForm getFeedbackByCategory(String category) {
        ResponseEntity<FeedbackForm> res = restTemplate.exchange(url + "/" + category, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                FeedbackForm.class);
        System.out.println("============================= GET FEEDBACK BY CATEGORY  =====================");
        System.out.println(res);
        return res.getBody();
    }

    //Update Feedback
    public FeedbackUpdateDTO updateFeedback(String category, String link) {
        HttpEntity entity = new HttpEntity(link, RequestFormat.createHeaders());
        ResponseEntity<FeedbackUpdateDTO> res = restTemplate
                .exchange(url + "/" + category + "?link=" + link,
                        HttpMethod.PUT,
                        entity,
                        new ParameterizedTypeReference<FeedbackUpdateDTO>() {
                });
        return res.getBody();
    }
}
