package com.mcclearningserver.controllers;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import com.mcclearningserver.dto.ClassResponseDTO;
import com.mcclearningserver.entities.MccClass;
import com.mcclearningserver.entities.Module;
import com.mcclearningserver.message.ResponseFile;
import com.mcclearningserver.services.ClassService;
import com.mcclearningserver.services.FileStorageService;
import com.mcclearningserver.services.ModuleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author ACER
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    ClassService classService;

    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/class")
    public List<ClassResponseDTO> getClassList() {
        return moduleService.getClassList();
    }

    //menampilkan semua module berdasarkan class (OPTIONAL) == dalam semua category
//    @GetMapping("/{idClass}")
//    public List<Module> getByIdClass(@PathVariable Integer idClass) {
//        return moduleService.getByIdClass(idClass);
//    }
    @GetMapping("/{idClass}")
    public Optional<MccClass> getClassById(@PathVariable Integer idClass) {
        return classService.getClassById(idClass);
    }

    //menampilkan list module berdasarkan class dan category 
    @GetMapping("/{idClass}/{category}")
    public List<Module> getByCategory(@PathVariable Integer idClass, @PathVariable String category) {
        return moduleService.getByIdClassAndCategory(idClass, category);
    }

    //trainer -- upload file module || DONE
    @PostMapping("/uploadFile")
    public ResponseFile uploadFile(@RequestParam("file") MultipartFile file, String title, String category, int idClass) {
        Module moduleRequest = new Module(title, category, idClass);
        Module moduleResponse = moduleService.storeModule(moduleRequest, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/module/downloadFile/")
                .path(moduleResponse.getIdModule().toString())
                .toUriString();

        return new ResponseFile(moduleResponse.getTitle(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    //trainee - download file || DONE
    @GetMapping("/downloadFile/{idModule}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer idModule) {
        // Load file from database
        Module moduleFile = moduleService.getModuleFile(idModule);
        String extension;

        try {
            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            MimeType type = allTypes.forName(moduleFile.getFileType());
            extension = type.getExtension();
        } catch (MimeTypeException e) {
            extension = " ";
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + moduleFile.getTitle() + extension + "\"")
                .header(HttpHeaders.CONTENT_TYPE, moduleFile.getFileType())
                .body(new ByteArrayResource(moduleFile.getFile()));
    }

//    @GetMapping("downloadFile/{idModule}")
//    public Module downloadModule(@PathVariable Integer idModule) {
//        return moduleService.getModuleFile(idModule);
//    }
    //trainer -- update module || DONE
    @PutMapping("/{idModule}")
    public ResponseFile updateModule(@PathVariable Integer idModule, @RequestParam("file") MultipartFile file, String title, String category, int idClass) {
        Module moduleRequest = new Module(title, category, idClass);
        Module moduleResponse = moduleService.updateModuleById(idModule, moduleRequest, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/module/downloadFile/")
                .path(moduleResponse.getIdModule().toString())
                .toUriString();

        return new ResponseFile(moduleResponse.getTitle(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
