package com.mcclearningclient.controllers;

import com.mcclearningclient.models.ModuleDTO;
import com.mcclearningclient.models.ProjectDTO;
import com.mcclearningclient.models.ResponseFileDTO;
import com.mcclearningclient.services.AnswerService;
import com.mcclearningclient.services.ExamService;
import com.mcclearningclient.services.ModuleService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    ExamService examService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AnswerService answerService;

    @GetMapping("/class")
    public String getClass(Model model) {
        Authentication authent = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("class", moduleService.getClassList());
        System.out.println("=== GET LEARNING CLASS (LAYER 1) " + authent.getName() + " " + model);
        System.out.println(moduleService.getClassList());
        return "learningClass/layer1";
    }

    @GetMapping("/getClass/{idClass}")
    public @ResponseBody
    List<Class> getByClassProcess(@PathVariable Integer idClass) {
        return moduleService.getByClass(idClass);
    }
    //====================================

    //========= GET ID CATEGORY ==============
    @GetMapping("/category")
    public String getCategory(Model model, Integer idClass) {
        model.addAttribute("class", moduleService.getClassById(idClass));
        System.out.println("=== GET LEARNING CLASS (LAYER 1)=== " + model);
        return "learningClass/layer2";
    }

    @GetMapping("/getCategory/{idClass}/{idCategory}")
    public @ResponseBody
    List<ModuleDTO> getByCategoryProcess(@PathVariable Integer idClass, @PathVariable String category) {
        return moduleService.getByCategory(idClass, category);
    }
    //====================================

    // ============== View Information category =======
    @GetMapping("/layer3")
    public String viewInformation(Model model, Integer idClass, String category) {
        Authentication authent = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee", authent.getName());
        model.addAttribute("module", moduleService.getByCategory(idClass, category)); //akan diganti oleh category berdasarkan server
        model.addAttribute("class", moduleService.getClassById(idClass));
        model.addAttribute("answer", answerService.getAnswerByIdEmployee(11));
        return "learningClass/layer3";
    }

//    @GetMapping("/downloadFile/{idModule}")
//    public boolean downloadModue
    // ============== Trainee -- View Information Module =======
    @GetMapping("/trainee")
    public String getAllModule() {
        return "learningClass/module-trainee";
    }

    // ============== Trainer -- View Information Module =======
    @GetMapping("/trainer")
    public String getAllModuleTrainer() {
        return "learningClass/module-trainer";
    }

    //============ trainer -- upload file module ==============
    @PostMapping(value = "/uploadFile")
    public @ResponseBody
    ResponseFileDTO uploadFile(MultipartFile file, String title, String category, Integer idClass) {
        return moduleService.uploadFile(file, title, category, idClass);
    }

    //========== trainee - download file ===============
//    @GetMapping("/downloadFile/{idModule}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Integer idModule) {
//        System.out.println("ID MODULE DOWNLOAD MODULE : " + idModule);
//        ModuleDTO moduleFile = moduleService.downloadModule(idModule);
//        
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + moduleFile.getTitle() + "\"")
//                .body(new ByteArrayResource(moduleFile.getFile()));
//    }
//    @GetMapping("/downloadFile/{idModule}")
//    public void downloadFile(@PathVariable Integer idModule) {
//        moduleService.downloadFile(idModule);
//    }
    @GetMapping("/downloadFile/{idModule}")
    public void downloadFile(@PathVariable Integer idModule) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8082/module/downloadFile/4", HttpMethod.GET, entity, byte[].class);
        Files.write(Paths.get("demo1.jpg"), response.getBody());
    }

    @PutMapping("/{idModule}")
    public @ResponseBody
    ResponseFileDTO updateModule(@PathVariable Integer idModule, MultipartFile file, String title, String category, Integer idClass) {
        return moduleService.updateModule(idModule, file, title, category, idClass);
    }

}
