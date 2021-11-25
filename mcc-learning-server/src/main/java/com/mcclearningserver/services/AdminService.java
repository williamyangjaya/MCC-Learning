/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.AllProfileDTO;
import com.mcclearningserver.dto.ProfileDTO;
import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Project;
import com.mcclearningserver.entities.ProjectFile;
import com.mcclearningserver.entities.Trainee;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.repositories.TraineeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AdminService {
    Project project;
    Trainee trainee;
    ProjectFile projectFile;
    
    @Autowired
    TraineeRepository traineeRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    //projectnya ada project controller yak. sama aja isinya, get all
    
    //data trainer
    public List<ProfileDTO> getAllTrainerProfile(){
        List<Employee> employee = employeeRepository.findAll();
        List<ProfileDTO> emp = new ArrayList<>();
        for(Employee e : employee){
            if(e.getIdTrainer()==null){
                ProfileDTO profile = new ProfileDTO(
                                    e.getIdEmployee(),
                                    e.getName(),
                                    e.getEmail(),
                                    e.getClass().getName()
                                    );
                emp.add(profile);
            }
        }
        return emp;
    }
    
    //data trainee
    public List<AllProfileDTO> getAllTraineeProfile(){
        List<Trainee> trainee = traineeRepository.findAll();
        List<AllProfileDTO> profile = new ArrayList<>();
        for(Trainee t : trainee){
            if (t.getEmployee().getIdTrainer() != null && t.getStatusMcc().contains("trainee")) {
                AllProfileDTO pro = new AllProfileDTO(
                t.getIdTrainee(),
                t.getEmployee().getName(),
                t.getEmployee().getEmail(),
                t.getEmployee().getPosition(),
                t.getBatch(),
                t.getEmployee().getIdClass().getName()
                );
                profile.add(pro);
            }
        }
        return profile;
    }
    
    //data alumni
    public List<AllProfileDTO> getAllAlumniProfile(){
        List<Trainee> trainee = traineeRepository.findAll();
        List<AllProfileDTO> profile = new ArrayList<>();
        for(Trainee t : trainee){
            if (t.getEmployee().getIdTrainer()!= null && t.getStatusMcc().contains("lulus")) {
            AllProfileDTO pro = new AllProfileDTO(
            t.getIdTrainee(),
            t.getEmployee().getName(),
            t.getEmployee().getEmail(),
            t.getEmployee().getPosition(),
            t.getBatch(),
            t.getEmployee().getIdClass().getName()
            );
            profile.add(pro);
            }
        }
        return profile;
    }
}
