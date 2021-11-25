package com.mcclearningclient.controllers;

import com.mcclearningclient.models.GetJudulDTO;
import com.mcclearningclient.models.LinkUpdateDTO;
import com.mcclearningclient.models.NewTitleUpdateDTO;
import com.mcclearningclient.models.ProjectDTO;
import com.mcclearningclient.models.RegisterProjectDTO;
import com.mcclearningclient.models.TitleUpdateDTO;
import com.mcclearningclient.models.ValidasiDTO;
import com.mcclearningclient.services.AdminService;
import com.mcclearningclient.services.HistoryService;
import com.mcclearningclient.services.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/finalProject")
public class ProjectController {
    
    @Autowired
    ProjectService projectService;
    
    @Autowired
    HistoryService historyService;
    
    @Autowired
    AdminService adminService;
    
    //================= CREATE PROJECT TRAINEE ===================
    @PostMapping("/trainee")
    public @ResponseBody
    String createProjectTrainee(@RequestBody RegisterProjectDTO registerProjectDTO) {
        System.out.println("=== BERHASIL CREATE ===");
        return projectService.createProjectTrainee(registerProjectDTO);
    }
    
    @GetMapping("/create")
    public String getSubmit() {
        return "/libraryProject/myProject";
    }
    //========================================================
    
    //============DATA GET ALL PROJECT || ADMIN ==================
    @GetMapping("/project")
    public String getAllProject(Model model) {
        model.addAttribute("project", projectService.getAllSearch());
        return "libraryProject/search-project";
    }
    
    @GetMapping("/get-project")
    public @ResponseBody List<ProjectDTO> getAllProject() {
        return projectService.getAllSearch();
    }
    //===============================================
    
    //========= SEARCH PROJECT ==============
    @GetMapping("/search-project")
    public String getAllSearch(Model model) {
        model.addAttribute("searchProject", projectService.getAllSearch());
        System.out.println("cetak search project");
        return "libraryProject/search-project";
    }

    @GetMapping("/cari")
    public @ResponseBody List<ProjectDTO> getAllSearchProcess() {
        return projectService.getAllSearch();
    }
    //====================================
    
    //------ GET TITLE APPROVAL (TRINER) -------------------
    @GetMapping("/title-submission")
    public String getAllTitleApproval(Model model) {
        model.addAttribute("titleSubmission", projectService.getAllTitleApproval());
        System.out.println("========== List Need Approval Title (Trainer) ============");
        return "libraryProject/title-submission";
    }

    @GetMapping("/title")
    public @ResponseBody
    List<GetJudulDTO> getTitle() {
        return projectService.getAllTitleApproval();
    }
    //-------------------------------------------------------
    
    //=============== GET LINK APPROVAL (TRAINER)==============
    @GetMapping("/get-project/{id}")
    public @ResponseBody
    ProjectDTO getProjectByIdProject(@PathVariable("id") Integer id) {
        System.out.println(id);
        return projectService.getProjectByIdProject(id);
    }
    
    @GetMapping("/full-project")
    public @ResponseBody
    List<ProjectDTO> getFullProject() {
        System.out.println("========== List Need Approval Link (Trainer) ============");
        return projectService.getAllLinkApproval();
    }
    
    @GetMapping("/project-submission")
    public String getAllLinkApproval(Model model) {
        model.addAttribute("project", projectService.getAllLinkApproval());
        return "libraryProject/project-submission";
    }
    //===============================================================================
    
    //===================== GET DETAIL PROJECT SETIAP TRAINEE (TRAINEE) ====================
    @GetMapping("/myProject/{id}")
    public @ResponseBody ProjectDTO getmyProject(@PathVariable("id") Integer id) {
        System.out.println("=== GET DETAIL PROJECT SETIAP TRAINEE ===");
        return projectService.getFullProjectByIdTrainee(id);
    }
   
    @GetMapping("/my-project")
    public String getMyProject(Model model) {
        Authentication authent = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("project", projectService.getFullProjectByIdTrainee(Integer.parseInt(authent.getName())));
        model.addAttribute("trainee", adminService.getAllTraineeProfile());
        System.out.println(projectService.getFullProjectByIdTrainee(Integer.parseInt(authent.getName())));
        return "libraryProject/myProject";
    }
    //=====================================
    
    //============== UPDATE JUDUL PROJECT (TRAINEE) =============================
    @GetMapping("/judul/{id}")
    public @ResponseBody GetJudulDTO getJudulByIdProject(@PathVariable("id") Integer id) {
        System.out.println(id);
        return projectService.getJudulByIdProject(id);
    }
    
    @PutMapping("/update-judul/{id}")
    public @ResponseBody 
        TitleUpdateDTO updateJudul(@PathVariable("id") Integer id, @RequestBody NewTitleUpdateDTO titleUpdateDTO) {
        System.out.println(" === update judul project === ");
        return projectService.updateJudul(id, titleUpdateDTO);
    }
    //==================================================================================
        
    //================= UPDATE LINK PROJECT (TRAINEE) ==========================
    @GetMapping("/{id}")
    public @ResponseBody
    ProjectDTO getById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return projectService.getProjectByIdProject(id);
    }
    
    @PutMapping("/update-link/{id}")
    public @ResponseBody
    LinkUpdateDTO updateLink(@PathVariable("id") Integer id, @RequestBody LinkUpdateDTO linkUpdateDTO) {
        System.out.println(" === Update Link Project ==== ");
        return projectService.updateLink(id, linkUpdateDTO);
    }
    //======================================================
    
    //============= VALIDASI JUDUL (TRAINER) ==============
    @PutMapping("/validasi-judul/{id}")
    public @ResponseBody
    ValidasiDTO validasiJudul(@PathVariable("id") Integer id, @RequestBody ValidasiDTO validasiDTO) {
        System.out.println(" === Validasi judul === ");
        return projectService.validasiJudul(id, validasiDTO);

    }
  
    //============= VALIDASI LINK (TRAINER) ==============
    @PutMapping("/validasi-link/{id}")
    public @ResponseBody
    ValidasiDTO validasiLink(@PathVariable("id") Integer id, @RequestBody ValidasiDTO validasiDTO) {
        System.out.println(" === Validasi link === ");
        return projectService.validasiLink(id, validasiDTO);

    }
}
