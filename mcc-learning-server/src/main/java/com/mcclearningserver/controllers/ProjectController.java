/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.GetJudulDTO;
import com.mcclearningserver.dto.LinkUpdateDTO;
import com.mcclearningserver.dto.ProjectDTO;
import com.mcclearningserver.dto.RegisterProjectDTO;
import com.mcclearningserver.dto.TitleUpdateDTO;
import com.mcclearningserver.dto.ValidasiDTO;
import com.mcclearningserver.entities.Project;
import com.mcclearningserver.entities.ProjectFile;
import com.mcclearningserver.payload.UploadFileResponse;
import com.mcclearningserver.services.ProjectService;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/finalProject")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    RegisterProjectDTO registerProjectDTO;
    GetJudulDTO getJudulDTO;

//    @PostMapping("/new")
//    public String createProjectTrainee(Authentication auth, @RequestBody RegisterProjectDTO registerProjectDTO){
//        return projectService.registerJudul(registerProjectDTO);
//    }
    //======registrasi project====== 
    //DONE
    @PostMapping("/new")
    public String createProjectTrainee(@RequestBody RegisterProjectDTO registerProjectDTO) throws MessagingException{
        return projectService.registerJudul(registerProjectDTO);
    }

    // get all biasa
    @GetMapping
    public List<Project> getAllProject() {
        return projectService.getAllProject();
    }

    //library, get all project ==
    //DONE
    @GetMapping("/library")
    public List<ProjectDTO> library() {
        return projectService.library();
    }

    // trainee side. detail project habis registrasi pertama ==
    //DONE
    @GetMapping("/trainee/{id}")
    public GetJudulDTO getJudulByIdTrainee(@PathVariable Integer id) {
        return projectService.getProjectByIdTrainee(id);
    }

    // trainee side. detail project ==
    //DONE
    @GetMapping("/full/trainee/{id}")
    public ProjectDTO getProjectByIdTrainee(@PathVariable Integer id) {
        return projectService.getFullProjectByIdTrainee(id);
    }

    //trainer side, list need approval title
    //DONE
    @GetMapping("/title-approval")
    public List<GetJudulDTO> getAllTitleApproval() {
        return projectService.getAllTitleApproval();
    }

    //trainer side, list need approval link
    //DONE
    @GetMapping("/link-approval")
    public List<ProjectDTO> getAllLinkApproval() {
        return projectService.getAllLinkApproval();
    }

    // trainer side. detail judul yg mau divalidasi trainer.
    //DONE
    @GetMapping("/{id}")
    public GetJudulDTO getJudulByIdProject(@PathVariable Integer id) {
        return projectService.getProjectByIdProject(id);
    }

    // trainer side. detail project yg mau divalidasi trainer. ==
    //DONE
    @GetMapping("/full/{id}")
    public ProjectDTO getProjectByIdProject(@PathVariable Integer id) {
        return projectService.getFullProjectByIdProject(id);
    }

    // trainee side . detail project masing-masig orang
    @GetMapping("/myProject/{id}")
    public ProjectDTO getFullProjectByIdTrainee(@PathVariable Integer id) {
        return projectService.getFullProjectByIdTrainee(id);
    }

    // trainee side. update judul ==
    //DONE
    @PutMapping("/title-update/{id}")
    public Project updateJudul(@PathVariable Integer id, @RequestBody TitleUpdateDTO project) throws MessagingException{
        return projectService.updateJudul(id, project);
    }

    // trainee side. update link
    //DONE
    @PutMapping("/link-update/{id}")
    public Project updateLink(@PathVariable Integer id, @RequestBody LinkUpdateDTO project) throws MessagingException{
        return projectService.updateLink(id, project);
    }

    // trainer side. validasi judul
    //DONE
    @PutMapping("/title-validation/{id}")
    public Project validasiJudul(@PathVariable Integer id, @RequestBody ValidasiDTO validasiDTO) throws MessagingException{
        return projectService.updateStatusJudul(id, validasiDTO);
    }

    // trainer side,. validasi link
    //DONE
    @PutMapping("/link-validation/{id}")
    public Project validasiLink(@PathVariable Integer id, @RequestBody ValidasiDTO validasiDTO) throws MessagingException{
        return projectService.updateStatusLink(id, validasiDTO);
    }

    //upload file
    @PostMapping("/uploadErd/{id}")
    public UploadFileResponse uploadErd(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {

        ProjectFile projectFile = projectService.uploadErd(file, id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                //                .path(projectFile.getIdFile())
                .toUriString();

        return new UploadFileResponse(fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
