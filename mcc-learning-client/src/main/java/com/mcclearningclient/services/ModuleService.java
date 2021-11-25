package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.MccClass;
import com.mcclearningclient.models.ModuleDTO;
import com.mcclearningclient.models.ResponseFileDTO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ModuleService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${api.url}/module")
    private String url;

    public MccClass getClassById(Integer id) {
        ResponseEntity<MccClass> res = restTemplate
                .exchange(url + "/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<MccClass>() {
                });
        System.out.println("CLASSSSSSSSSSSS = " + res.getBody());
        return res.getBody();
    }

    //get list of module by class id (ex: /1 for java, /2 for .net) || DONE
    public List<Class> getByClass(Integer idClass) {
        ResponseEntity<List<Class>> res = restTemplate
                .exchange(url + "/" + idClass, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<Class>>() {
                });
        return res.getBody();
    }

    public List<MccClass> getClassList() {
        ResponseEntity<List<MccClass>> res = restTemplate
                .exchange(url + "/class", HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<MccClass>>() {
                });
        System.out.println("RESPONSE : ======== " + res.getBody());
        return res.getBody();
    }

    //get list of module by class id by category (ex: 1/basic for java basic, 2/basic for .net basic) || DONE
    public List<ModuleDTO> getByCategory(Integer idClass, String category) {
        System.out.println("REQUESTT : " + idClass + "/" + category);
        ResponseEntity<List<ModuleDTO>> res = restTemplate
                .exchange(url + "/" + idClass + "/" + category, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ModuleDTO>>() {
                });
        System.out.println("CLIENTTTTTTTT : " + res.getBody());
        return res.getBody();
    }

    //trainer -- upload file module
    public ResponseFileDTO uploadFile(MultipartFile file, String title, String category, int idClass) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<ResponseFileDTO> res = restTemplate.exchange(url + "/uploadFile", HttpMethod.POST, entity,
                new ParameterizedTypeReference<ResponseFileDTO>() {
        });
        return res.getBody();
    }

    //trainee - download file
    public boolean downloadFile(Integer idModule) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            ResponseEntity<byte[]> response = restTemplateBuilder.build()
                    .exchange(url + "/downloadFile/" + idModule, HttpMethod.POST, entity, byte[].class);
            Files.write(Paths.get("D:\\Data\\Downloads\\module.xlsx"), response.getBody());
            System.out.println(response.getBody());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void downloadModule(Integer idModule) {
        ResponseEntity<Resource> res = restTemplate
                .exchange(url + "/downloadFile/" + idModule, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<Resource>() {
                });
        System.out.println("CLASSSSSSSSSSSS = " + res.getBody());
    }
    
    //trainer -- update module
    public ResponseFileDTO updateModule(Integer idModule, MultipartFile file, String title, String category, Integer idClass) {
        HttpEntity entity = new HttpEntity(RequestFormat.createHeaders());
        ResponseEntity<ResponseFileDTO> res = restTemplate.exchange(url + "/" + idModule, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<ResponseFileDTO>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

}
