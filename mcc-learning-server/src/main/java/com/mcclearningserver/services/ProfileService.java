/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.ChangePwDTO;
import com.mcclearningserver.dto.ProfileDTO;
import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Trainee;
import com.mcclearningserver.entities.User;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.repositories.TraineeRepository;
import com.mcclearningserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ProfileService {

    Employee employee;
    Trainee trainee;
    User user;
    ProfileDTO profileDTO;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public ProfileDTO myProfile(Integer id) {
        Trainee trainee = traineeRepository.getById(id);
        ProfileDTO profile = new ProfileDTO(
                trainee.getEmployee().getName(),
                trainee.getEmployee().getEmail(),
                trainee.getEmployee().getPosition(),
                trainee.getBatch(),
                trainee.getEmployee().getIdClass().getName()
        );
        return profile;
    }

    public ProfileDTO trainerProfile(Integer id) {
        Employee emp = employeeRepository.getById(id);
        ProfileDTO profile = new ProfileDTO(
                emp.getName(),
                emp.getEmail(),
                emp.getPosition()
        );
        return profile;
    }

    public String changePassword(ChangePwDTO changePwDTO, Integer id) throws Exception {
        User getpw = userRepository.getById(id);

        if (!(passwordEncoder.matches(changePwDTO.getOldPassword(), getpw.getPassword()))) {
            throw new Exception("Wrong Pasword");
        } else {
            if (changePwDTO.getNewPassword().equals(changePwDTO.getRepeatNewPassword())) {
                getpw.setPassword(passwordEncoder.encode(changePwDTO.getNewPassword()));
                userRepository.save(getpw);
                return "Password Changed";
            } else {
                return "New Password Did Not Match";
            }
        }
    }
}
