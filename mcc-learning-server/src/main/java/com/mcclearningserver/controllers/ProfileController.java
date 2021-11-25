/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.ChangePwDTO;
import com.mcclearningserver.dto.ProfileDTO;
import com.mcclearningserver.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author William Yangjaya
 */
@RestController
@RequestMapping("/myprofile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public ProfileDTO myProfile(@PathVariable Integer id) {
        return profileService.myProfile(id);
    }

    @GetMapping("/trainer/{id}")
    public ProfileDTO trainerProfile(@PathVariable Integer id) {
        return profileService.trainerProfile(id);
    }

    @PutMapping("/changepassword/{id}")
    public ResponseEntity<String> changePassword(@RequestBody ChangePwDTO changePwDTO, @PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(profileService.changePassword(changePwDTO, id), HttpStatus.OK);
    }
}
