package com.mcclearningclient.services;

import com.mcclearningclient.configs.RequestFormat;
import com.mcclearningclient.models.GetJudulDTO;
import com.mcclearningclient.models.LinkUpdateDTO;
import com.mcclearningclient.models.NewTitleUpdateDTO;
import com.mcclearningclient.models.ProjectDTO;
import com.mcclearningclient.models.RegisterProjectDTO;
import com.mcclearningclient.models.TitleUpdateDTO;
import com.mcclearningclient.models.ValidasiDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}/finalProject")
    private String url;

    //CREATE PROJECT TRAINEE ||DONE
    public String createProjectTrainee(RegisterProjectDTO registerProjectDTO) {
        HttpEntity entity = new HttpEntity(registerProjectDTO, RequestFormat.createHeaders());
        ResponseEntity<String> res = restTemplate.exchange(url + "/new", HttpMethod.POST, entity,
                new ParameterizedTypeReference<String>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

    //GET ALL PROJECT
    public List<ProjectDTO> getAllProject() {
        ResponseEntity<List<ProjectDTO>> response = restTemplate
                .exchange(url, HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ProjectDTO>>() {
                });
        System.out.println("========== GET ALL PROJECT  ============");
        return response.getBody();

    }

    //SEARCH PROJECT ||DONE
    public List<ProjectDTO> getAllSearch() {
        ResponseEntity<List<ProjectDTO>> response = restTemplate
                .exchange(url + "/library", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ProjectDTO>>() {
                });
        System.out.println("========== Search Project Trainee ============");
        return response.getBody();

    }

    //GET JUDUL BY ID TRAINEE
    // trainee side. detail project habis registrasi pertama
    public GetJudulDTO getJudulByIdTrainee(Integer id) {
        System.out.println(" === GET JUDUL BY ID TRAINEE");
        return restTemplate.getForEntity(url + "/trainee/" + id, GetJudulDTO.class).getBody();
    }

    //GET PROJECT BY ID TRAINEE
    // trainee side. detail project
    public ProjectDTO getProjectByIdTrainee(Integer id) {
        System.out.println(" === GET PROJECT BY ID TRAINEE === ");
        ProjectDTO res = restTemplate.getForEntity(url + "/full/trainee/" + id, ProjectDTO.class).getBody();
        System.out.println(res);
        return res;
    }

    //GET TITLE APPROVAL ||DONE
    //trainer side, list need approval title
    public List<GetJudulDTO> getAllTitleApproval() {
        ResponseEntity<List<GetJudulDTO>> response = restTemplate
                .exchange(url + "/title-approval", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<GetJudulDTO>>() {
                });
        System.out.println("========== List Need Approval Title (Trainer) ============");
        return response.getBody();
    }

    //GET LINK APPROVAL ||DONE
    //trainer side, list need approval link
    public List<ProjectDTO> getAllLinkApproval() {
        ResponseEntity<List<ProjectDTO>> response = restTemplate
                .exchange(url + "/link-approval", HttpMethod.GET,
                        new HttpEntity(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<ProjectDTO>>() {
                });
        System.out.println("========== List Need Approval Link (Trainer) ============");
        return response.getBody();
    }

    //GET JUDUL BY ID PROJECT ||DONE
    // trainer side. detail judul yg mau divalidasi trainer
    public GetJudulDTO getJudulByIdProject(Integer id) {
        System.out.println(" === GET JUDUL BY ID PROJECT");
        return restTemplate.getForEntity(url + "/" + id, GetJudulDTO.class).getBody();
    }

    //GET FULL PROJECT BY ID PROJECT ||DONE
    // trainer side. detail project yg mau divalidasi trainer
    public ProjectDTO getProjectByIdProject(Integer id) {
        System.out.println(" === GET PROJECT BY ID PROJECT === ");
        ProjectDTO res = restTemplate.getForEntity(url + "/full/" + id, ProjectDTO.class).getBody();
        System.out.println(res);
        return res;
    }



    //GET DETAIL PROJECT SETIAP TRAINEE ||DONE
    //trainee side . detail project masing-masig orang   
     public ProjectDTO getFullProjectByIdTrainee(Integer id) {
         
             ResponseEntity<ProjectDTO> res = restTemplate
                .exchange(url + "/myProject/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<ProjectDTO>() {
                });
         return res.getBody();
         /*
         } catch (NullPointerException e) {
             System.out.println("#Pesan Eror" +e);
         } finally {
             System.out.println("#Pesan EROR Finally");
             return objectDummy;
         } */
    }

    //UPDATE JUDUL ||DONE
    public TitleUpdateDTO updateJudul(Integer id, NewTitleUpdateDTO titleUpdateDTO) {
        HttpEntity entity = new HttpEntity(titleUpdateDTO, RequestFormat.createHeaders());
        ResponseEntity<TitleUpdateDTO> res = restTemplate.exchange(url + "/title-update/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<TitleUpdateDTO>() {
        });
        return res.getBody();
    }

    //UPDATE LINK ||DONE
    public LinkUpdateDTO updateLink(Integer id, LinkUpdateDTO linkUpdateDTO) {
        HttpEntity entity = new HttpEntity(linkUpdateDTO, RequestFormat.createHeaders());
        ResponseEntity<LinkUpdateDTO> res = restTemplate.exchange(url + "/link-update/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<LinkUpdateDTO>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }

    //VALIDASI JUDUL ||DONE
    public ValidasiDTO validasiJudul(Integer id, ValidasiDTO validasiDTO) {
        HttpEntity entity = new HttpEntity(validasiDTO, RequestFormat.createHeaders());
        ResponseEntity<ValidasiDTO> res = restTemplate.exchange(url + "/title-validation/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<ValidasiDTO>() {
        });
        return res.getBody();
    }

    //VALIDASI LINK ||DONE
    public ValidasiDTO validasiLink(Integer id, ValidasiDTO validasiDTO) {
        HttpEntity entity = new HttpEntity(validasiDTO, RequestFormat.createHeaders());
        ResponseEntity<ValidasiDTO> res = restTemplate.exchange(url + "/link-validation/" + id, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<ValidasiDTO>() {
        });
        System.out.println(res.getBody());
        return res.getBody();
    }
}
