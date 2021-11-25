/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.ChangePassword;
import com.mcclearningclient.models.Profile;
import com.mcclearningclient.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author William Yangjaya
 */
@Controller
@RequestMapping("/myprofile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("")
    public String viewMyProfile() {
        return "myprofile";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Profile getByIdUser(@PathVariable("id") Integer id) {
        return profileService.getMyProfile(id);
    }

    @GetMapping("/trainer/{id}")
    public @ResponseBody
    Profile trainerProfile(@PathVariable("id") Integer id) {
        return profileService.getTrainerProfile(id);
    }

    @GetMapping("/changepassword")
    public String viewChangePasswordForm() {
        return "changepassword";
    }

    @PutMapping("/changepassword/{id}")
    public @ResponseBody
    String changePassword(ChangePassword changePassword, @PathVariable("id") Integer id) {
        return profileService.updatePassword(id, changePassword);
    }
}
