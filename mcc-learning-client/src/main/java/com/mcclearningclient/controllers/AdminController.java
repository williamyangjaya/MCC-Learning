package com.mcclearningclient.controllers;

import com.mcclearningclient.models.AllProfileDTO;
import com.mcclearningclient.models.ProfileDTO;
import com.mcclearningclient.services.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    //============DATA GET ALL TRAINER==================
    @GetMapping("/trainer")
    public String getAllTrainerProfile(Model model) {
        model.addAttribute("trainer", adminService.getAllTrainerProfile());
        return "admin/data-trainer";
    }
    
    @GetMapping("/get-trainer")
    public @ResponseBody List<ProfileDTO> getAllTrainer() {
        return adminService.getAllTrainerProfile();
    }
    //===============================================
    
    //============DATA GET ALL TRAINEE==================
    @GetMapping("/trainee")
    public String getAllTraineeProfile(Model model) {
        model.addAttribute("trainee", adminService.getAllTraineeProfile());
        return "admin/data-trainee";
    }
    
    @GetMapping("/get-trainee")
    public @ResponseBody List<AllProfileDTO> getAllTrainee() {
        return adminService.getAllTraineeProfile();
    }
    //===============================================
    
    //============DATA GET ALL ALUMNI==================
    @GetMapping("/alumni")
    public String getAllAlumniProfile(Model model) {
        model.addAttribute("alumni", adminService.getAllAlumniProfile());
        return "admin/data-alumni";
    }
    
    @GetMapping("/get-alumni")
    public @ResponseBody List<AllProfileDTO> getAllAlumni() {
        return adminService.getAllAlumniProfile();
    }
    //===============================================
    
}
