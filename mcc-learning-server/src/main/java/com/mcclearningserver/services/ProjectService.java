/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.GetJudulDTO;
import com.mcclearningserver.dto.LinkUpdateDTO;
import com.mcclearningserver.dto.ProjectDTO;
import com.mcclearningserver.dto.RegisterProjectDTO;
import com.mcclearningserver.dto.TitleUpdateDTO;
import com.mcclearningserver.dto.ValidasiDTO;
import com.mcclearningserver.entities.History;
import com.mcclearningserver.entities.Project;
import com.mcclearningserver.entities.ProjectFile;
import com.mcclearningserver.entities.Status;
import com.mcclearningserver.entities.Trainee;
import com.mcclearningserver.repositories.HistoryRepository;
import com.mcclearningserver.repositories.ProjectFileRepository;
import com.mcclearningserver.repositories.ProjectRepository;
import com.mcclearningserver.repositories.TraineeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.mcclearningserver.exception.FileStorageException;
import java.io.IOException;
import javax.mail.MessagingException;

/**
 *
 * @author ASUS
 */
@Service
public class ProjectService {

    Project project;
    Trainee trainee;
    ProjectFile projectFile;
    
    @Autowired
    NotificationService notificationService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ProjectFileRepository projectFileRepository;
    

    RegisterProjectDTO registerProjectDTO;
    GetJudulDTO getJudulDTO;

    //=====register judul, trainer side. pas pertama mau registrasi final project=====
    public String registerJudul(RegisterProjectDTO registerProjectDTO) throws MessagingException{
        projectRepository.save(new Project(
                registerProjectDTO.getJudul(),
                registerProjectDTO.getDeskripsi(),
                new Status(1)
        ));
        Trainee addProjectSatu = traineeRepository.findById(registerProjectDTO.getIdMccSatu()).get();
        addProjectSatu.setIdProject(new Project(projectRepository.findAll().get(projectRepository.findAll().size() - 1).getIdProject()));
        traineeRepository.save(addProjectSatu);

        if (registerProjectDTO.getIdMccDua() != 0) {
            Trainee addProjectDua = traineeRepository.findById(registerProjectDTO.getIdMccDua()).get();
            addProjectDua.setIdProject(new Project(projectRepository.findAll().get(projectRepository.findAll().size() - 1).getIdProject()));
            traineeRepository.save(addProjectDua);
        } else {

        }

        if (registerProjectDTO.getIdMccTiga() != 0) {
            Trainee addProjectTiga = traineeRepository.findById(registerProjectDTO.getIdMccTiga()).get();
            addProjectTiga.setIdProject(new Project(projectRepository.findAll().get(projectRepository.findAll().size() - 1).getIdProject()));
            traineeRepository.save(addProjectTiga);
        } else {

        }
        Integer trainee = traineeRepository.findById(registerProjectDTO.getIdMccSatu()).get().getEmployee().getIdEmployee();
        System.out.println("ID TRAINEE: "+trainee);
//        notificationService.notifRegisJudul(trainee);
        return "Registrasi Judul Berhasil";
    }

    //=====find all biasa=====
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    //Library . All side
    public List<ProjectDTO> library() {
        List<Project> project = projectRepository.findAll();
        List<ProjectDTO> pdds = new ArrayList<>();
        for (Project p : project) {
            if (p.getSchema() != null) {
                List<String> nama = new ArrayList<>();
                for (Trainee t : p.getTraineeList()) {
                    nama.add(t.getEmployee().getName());
                }
                ProjectDTO td = new ProjectDTO(
                        p.getIdProject(),
                        p.getTitle(),
                        p.getDescription(),
                        p.getErd(),
                        p.getUml(),
                        p.getSchema(),
                        p.getLink(),
                        nama,
                        p.getTraineeList().get(0).getEmployee().getIdTrainer().getName(),
                        p.getTraineeList().get(0).getBatch());
                pdds.add(td);
            }
        }
        return pdds;
    }

    //need title approval, Trainer Side
    public List<GetJudulDTO> getAllTitleApproval() {
        List<Project> project = projectRepository.findAll();
        List<GetJudulDTO> pdds = new ArrayList<>();
        for (Project e : project) {
            if (e.getLink() == null && e.getCurrentStatus().getIdStatus() == 1) {
                List<String> nama = new ArrayList<>();
                for (Trainee t : e.getTraineeList()) {
                    nama.add(t.getEmployee().getName());
                }
                GetJudulDTO td = new GetJudulDTO(
                        e.getIdProject(),
                        e.getTitle(),
                        e.getTraineeList().get(0).getBatch(),
                        nama,
                        e.getTraineeList().get(0).getEmployee().getIdTrainer().getName(),
                        e.getDescription()
                );
                pdds.add(td);
            }
        }
        return pdds;
    }

    //link need approval, Trainer side
    public List<ProjectDTO> getAllLinkApproval() {
        List<Project> project = projectRepository.findAll();
        List<ProjectDTO> pdds = new ArrayList<>();
        for (Project p : project) {
            if (p.getSchema() != null) {
                if (p.getCurrentStatus().getIdStatus() != 6) {
                    List<String> nama = new ArrayList<>();
                    for (Trainee t : p.getTraineeList()) {
                        nama.add(t.getEmployee().getName());
                    }
                    ProjectDTO td = new ProjectDTO(
                            p.getIdProject(),
                            p.getTitle(),
                            p.getDescription(),
                            p.getErd(),
                            p.getUml(),
                            p.getSchema(),
                            p.getLink(),
                            nama,
                            p.getTraineeList().get(0).getEmployee().getIdTrainer().getName(),
                            p.getTraineeList().get(0).getBatch());
                    pdds.add(td);
                }
            }
        }
        return pdds;
    }

    //=====trainee side. detail project habis registrasi pertama=====
    public GetJudulDTO getProjectByIdTrainee(Integer id) {
        Integer idProject = traineeRepository.getById(id).getIdProject().getIdProject();
        Project e = projectRepository.findById(idProject).get();
        List<String> nama = new ArrayList<>();
        for (Trainee t : e.getTraineeList()) {
            nama.add(t.getEmployee().getName());
        }
        GetJudulDTO td = new GetJudulDTO(
                e.getIdProject(),
                e.getTitle(),
                e.getTraineeList().get(0).getBatch(),
                nama,
                e.getTraineeList().get(0).getEmployee().getIdTrainer().getName(),
                e.getDescription()
        );
        return td;
    }

    //=====trainer side. detail judul yg mau divalidasi trainer.=====
    public GetJudulDTO getProjectByIdProject(Integer id) {
        Project e = projectRepository.findById(id).get();
        List<String> nama = new ArrayList<>();
        for (Trainee t : e.getTraineeList()) {
            nama.add(t.getEmployee().getName());
        }
        GetJudulDTO td = new GetJudulDTO(
                e.getIdProject(),
                e.getTitle(),
                e.getTraineeList().get(0).getBatch(),
                nama,
                e.getTraineeList().get(0).getEmployee().getIdTrainer().getName(),
                e.getDescription()
        );
        return td;
    }

    //=====trainer side, modal pas mau validasi, detail project. di get by id project
    public ProjectDTO getFullProjectByIdProject(Integer idProject) {
        Project e = projectRepository.findById(idProject).get();
        List<String> nama = new ArrayList<>();
        for (Trainee t : e.getTraineeList()) {
            nama.add(t.getEmployee().getName());
        }
        ProjectDTO td = new ProjectDTO(
                e.getIdProject(),
                e.getTitle(),
                e.getDescription(),
                e.getErd(),
                e.getUml(),
                e.getSchema(),
                e.getLink(),
                nama,
                e.getTraineeList().get(0).getEmployee().getIdTrainer().getName()
        );
        return td;
    }

    //=====trainee side, detail project masing2 orang=====
    public ProjectDTO getFullProjectByIdTrainee(Integer id) {
        if (traineeRepository.getById(id).getIdProject() != null) {
            Integer idProject = traineeRepository.getById(id).getIdProject().getIdProject();
            Project e = projectRepository.findById(idProject).get();
            List<String> nama = new ArrayList<>();
            for (Trainee t : e.getTraineeList()) {
                nama.add(t.getEmployee().getName());
            }
            ProjectDTO td = new ProjectDTO(
                    e.getIdProject(),
                    e.getTitle(),
                    e.getDescription(),
                    e.getErd(),
                    e.getUml(),
                    e.getSchema(),
                    e.getLink(),
                    nama,
                    e.getTraineeList().get(0).getEmployee().getIdTrainer().getName()
            );
            return td;
        } else {
            ProjectDTO abc = new ProjectDTO();
            return abc;
        }
    }

//=====modal update judul, trainee side=====
    public Project updateJudul(Integer id, TitleUpdateDTO project) throws MessagingException{
        Project updateProject = projectRepository.findById(id).get();
        updateProject.setTitle(project.getJudul());
        updateProject.setDescription(project.getDeskripsi());
        updateProject.setCurrentStatus(new Status(1));

        Integer trainee = traineeRepository.findById(updateProject.getTraineeList().get(0).getIdTrainee()).get().getEmployee().getIdEmployee();

        System.out.println(trainee);

//        notificationService.notifUpdateJudul(trainee);
        historyRepository.save(new History(
                java.util.Calendar.getInstance().getTime(),
                new Project(id),
                new Status(1)
        ));

        return projectRepository.save(updateProject);
    }

    //=====modal update link, trainee side=====
    public Project updateLink(Integer id, LinkUpdateDTO project) throws MessagingException{
        Project updateProject = projectRepository.findById(id).get();
        updateProject.setErd(project.getErd());
        updateProject.setUml(project.getUml());
        updateProject.setSchema(project.getSkema());
        updateProject.setLink(project.getLink());
        updateProject.setCurrentStatus(new Status(4));

        Integer trainee = traineeRepository.findById(updateProject.getTraineeList().get(0).getIdTrainee()).get().getEmployee().getIdEmployee();

        System.out.println(trainee);

//        notificationService.notifUpdateLink(trainee);
        historyRepository.save(new History(
                java.util.Calendar.getInstance().getTime(),
                new Project(id),
                new Status(4)
        ));

        return projectRepository.save(updateProject);
    }

    //=====validasi judul triner side=====
    public Project updateStatusJudul(Integer id, ValidasiDTO validasiDTO) throws MessagingException{
        //Integer id = employeeRepository.findById(validasiDTO.getIdMcc()).get().getTrainee().getIdProject().getIdProject();
        Project updateProject = projectRepository.findById(id).get();
        if (validasiDTO.isStatus() == true) {
            updateProject.setCurrentStatus(new Status(3));
            for (Trainee t : updateProject.getTraineeList()) {
                Integer trainee = t.getEmployee().getIdEmployee();
//                notificationService.notifValidasiDiterima(trainee);
            }
            historyRepository.save(new History(
                    java.util.Calendar.getInstance().getTime(),
                    validasiDTO.getNote(),
                    new Project(id),
                    new Status(3)
            ));
        } else {
            updateProject.setCurrentStatus(new Status(2));
            for (Trainee t : updateProject.getTraineeList()) {
                Integer trainee = t.getEmployee().getIdEmployee();
//                notificationService.notifValidasiDitolak(trainee);
            }
            historyRepository.save(new History(
                    java.util.Calendar.getInstance().getTime(),
                    validasiDTO.getNote(),
                    new Project(id),
                    new Status(2)
            ));
        }

        return projectRepository.save(updateProject);
    }

    //=====validasi project trainer side=====
    public Project updateStatusLink(Integer id, ValidasiDTO validasiDTO) throws MessagingException{
        System.out.println(validasiDTO.isStatus());
        System.out.println(validasiDTO.getNote());
        Project updateProject = projectRepository.findById(id).get();
        if (validasiDTO.isStatus() == true) {
            updateProject.setCurrentStatus(new Status(6));
            for (Trainee t : updateProject.getTraineeList()) {
                Integer trainee = t.getEmployee().getIdEmployee();
                traineeRepository.findById(trainee).get().setStatusMcc("lulus");
//                notificationService.notifValidasiDiterima(trainee);
            }
            historyRepository.save(new History(
                    java.util.Calendar.getInstance().getTime(),
                    validasiDTO.getNote(),
                    new Project(id),
                    new Status(6)
            ));
        } else {
            updateProject.setCurrentStatus(new Status(5));
            for (Trainee t : updateProject.getTraineeList()) {
                Integer trainee = t.getEmployee().getIdEmployee();
//                notificationService.notifValidasiDitolak(trainee);
            }
            historyRepository.save(new History(
                    java.util.Calendar.getInstance().getTime(),
                    validasiDTO.getNote(),
                    new Project(id),
                    new Status(5)
            ));
        }

        return projectRepository.save(updateProject);
    }

    //ini untuk yg upload file, tp baru 1 file
    public ProjectFile uploadErd(MultipartFile file, Integer id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ProjectFile uploadErd = projectFileRepository.getById(id);
            uploadErd.setErdFile(file.getBytes());
//            ProjectFile projectFile = new ProjectFile(file.getBytes());
            System.out.println("Upload Success");
            return projectFileRepository.save(uploadErd);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
