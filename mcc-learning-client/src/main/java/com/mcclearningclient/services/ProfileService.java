/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.ChangePassword;
import com.mcclearningclient.models.Profile;
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
 * @author William Yangjaya
 */
@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}/myprofile")
    private String url;

    public Profile getMyProfile(Integer id) {
        ResponseEntity<Profile> res = restTemplate
                .exchange(url + "/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<Profile>() {
                });
        return res.getBody();
    }

    public Profile getTrainerProfile(Integer id) {
        ResponseEntity<Profile> res = restTemplate
                .exchange(url + "/trainer/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<Profile>() {
                });
        return res.getBody();
    }

    public String updatePassword(Integer id, ChangePassword changePassword) {
        HttpEntity entity = new HttpEntity(changePassword, RequestFormat.createHeaders());
        ResponseEntity<String> res = restTemplate.exchange(url + "/changepassword/" + id, HttpMethod.PUT, entity, new ParameterizedTypeReference<String>() {
        });
        return res.getBody();
    }

}
