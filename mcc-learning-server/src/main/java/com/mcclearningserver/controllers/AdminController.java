/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.AllProfileDTO;
import com.mcclearningserver.dto.ProfileDTO;
import com.mcclearningserver.services.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    AdminService adminService;
    
    @GetMapping("/trainer")
    public List<ProfileDTO> getAllTrainerProfile(){
        return adminService.getAllTrainerProfile();
    }
    
    @GetMapping("/trainee")
    public List<AllProfileDTO> getAllTraineeProfile(){
        return adminService.getAllTraineeProfile();
    }
    
    @GetMapping("/alumni")
    public List<AllProfileDTO> getAllAlumniProfile(){
        return adminService.getAllAlumniProfile();
    }
}
