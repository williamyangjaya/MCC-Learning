/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.HistoryDTO;
import com.mcclearningserver.entities.History;
import com.mcclearningserver.entities.Project;
import com.mcclearningserver.entities.User;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.repositories.HistoryRepository;
import com.mcclearningserver.repositories.ProjectRepository;
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
public class HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    LoginService loginService;
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    TraineeRepository traineeRepository;
    
    //dear aul, ini sama persis yak sama project yg sebelumnya
    
    public List<HistoryDTO> getHistoryByIdProject(Integer idMcc) {
        Project idProject = traineeRepository.getById(idMcc).getIdProject();
        List<History> history = historyRepository.findAllByIdProject(idProject);
        List<HistoryDTO> pdds = new ArrayList<>();
        for (History e : history) {
            if (e.getIdProject() != null) {
                HistoryDTO td = new HistoryDTO(
                        e.getIdHistory(),
                        e.getIdProject().getIdProject(),
                        e.getHistoryTime(),
                        e.getNote(),
                        e.getIdStatus().getStatus()
                );
                pdds.add(td);
            }
        }
        return pdds;
    }
    
    public List<HistoryDTO> getHistoryByIdMcc(Integer id) {
        //User user = loginService.loadUserByUsername(id);
        Project idProject = traineeRepository.getById(id).getIdProject();
        List<History> history = historyRepository.findAllByIdProject(idProject);
        List<HistoryDTO> pdds = new ArrayList<>();
        for (History e : history) {
            if (e.getIdProject() != null) {
                HistoryDTO td = new HistoryDTO(
                        e.getIdHistory(),
                        e.getIdProject().getIdProject(),
                        e.getHistoryTime(),
                        e.getNote(),
                        e.getIdStatus().getStatus()
                );
                pdds.add(td);
            }
        }
        System.out.println("bebas apaan aja");
        return pdds;
    }
    
    public List<HistoryDTO> getAllHistory() {
        List<History> history = historyRepository.findAll();
        List<HistoryDTO> pdds = new ArrayList<>();
        for (History e : history) {
            if (e.getIdProject() != null) {
                HistoryDTO td = new HistoryDTO(
                        e.getIdHistory(),
                        e.getIdProject().getIdProject(),
                        e.getHistoryTime(),
                        e.getNote(),
                        e.getIdStatus().getStatus()
                );
                pdds.add(td);
            }
        }
        return pdds;
    }
}
