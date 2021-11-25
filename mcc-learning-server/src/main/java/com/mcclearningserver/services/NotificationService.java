/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Project;
import com.mcclearningserver.entities.Trainee;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.repositories.ProjectRepository;
import com.mcclearningserver.repositories.TraineeRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    EmployeeRepository employeeRepository;
    
    @Autowired
    TraineeRepository traineeRepository;
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Value("${spring.mail.username")
    String sender;
    
    @Autowired
    public NotificationService(JavaMailSender javaMailSender, EmployeeRepository employeeRepository) {
        this.javaMailSender = javaMailSender;
        this.employeeRepository = employeeRepository;
    }
    
    public void notifRegisJudul (Integer idMcc) throws MessagingException{
        
        Trainee trainee = traineeRepository.findById(idMcc).get();
        Integer idTrainer = employeeRepository.findById(idMcc).get().getIdTrainer().getIdEmployee();
        Employee trainer = employeeRepository.findById(idTrainer).get();
        Project project = projectRepository.findById(trainee.getIdProject().getIdProject()).get();
        System.out.println("trainee : "+trainee);
        System.out.println("idTrainer : "+idTrainer);
        System.out.println("trainer : "+trainer);
        System.out.println("project : "+project);
        System.out.println("judul : "+project.getTitle());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(trainer.getEmail());
        helper.setSubject("Registrasi Judul");
        message.setText(String.format("Registrasi Judul : %s", project.getTitle()), "UTF-8", "html");
        helper.setSubject("[Registrasi Judul]");
        message.setText(String.format("Dear, kak %s"
                        +"<br>"
                        + "<br>"
                        +"Telah dilakukan registrasi Final Project dengan judul : <b>%s</b>"
                        + "<br>"
                        +"Mohon untuk lakukan pengecekan dan validasi terhadap judul tersebut di laman Title Submission."
                        + "<br>"
                        +"Terima kasih.", trainer.getName(), project.getTitle()
                        ), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
    public void notifUpdateProject (Integer idMcc) throws MessagingException{
        
        Employee employee = employeeRepository.findById(idMcc).get();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(employee.getEmail());
        helper.setSubject("Update Project Akhir");
        message.setText(String.format("Update project akhir Trainee MCC"), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
    public void notifUpdateJudul (Integer idMcc) throws MessagingException{
        
        Trainee trainee = traineeRepository.findById(idMcc).get();
        Integer idTrainer = employeeRepository.findById(idMcc).get().getIdTrainer().getIdEmployee();
        Employee trainer = employeeRepository.findById(idTrainer).get();
        Project project = projectRepository.findById(trainee.getIdProject().getIdProject()).get();
//        Employee employee = employeeRepository.findById(idMcc).get();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(trainer.getEmail());
        helper.setSubject("Update Judul Project Akhir");
        message.setText(String.format("Dear, kak %s"
                        +"<br>"
                        + "<br>"
                        +"Telah dilakukan update <b>judul</b> Final Project dengan judul : <b>%s</b>"
                        + "<br>"
                        +"Mohon untuk lakukan pengecekan dan validasi terhadap judul tersebut di laman Title Submission."
                        + "<br>"
                        +"Terima kasih.", trainer.getName(), project.getTitle()
                        ), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
    public void notifUpdateLink (Integer idMcc) throws MessagingException{
        
        Trainee trainee = traineeRepository.findById(idMcc).get();
        Integer idTrainer = employeeRepository.findById(idMcc).get().getIdTrainer().getIdEmployee();
        Employee trainer = employeeRepository.findById(idTrainer).get();
        Project project = projectRepository.findById(trainee.getIdProject().getIdProject()).get();
//        Employee employee = employeeRepository.findById(idMcc).get();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(trainer.getEmail());
        helper.setSubject("Update Link Project Akhir");
        message.setText(String.format("Dear, kak %s"
                        +"<br>"
                        + "<br>"
                        +"Telah dilakukan update <b>link</b> Final Project dengan judul : <b>%s</b>"
                        + "<br>"
                        +"Mohon untuk lakukan pengecekan dan validasi terhadap judul tersebut di laman Title Submission."
                        + "<br>"
                        +"Terima kasih.", trainer.getName(), project.getTitle()
                        ), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
    public void notifValidasiDitolak (Integer idMcc) throws MessagingException{
        Trainee trainee = traineeRepository.findById(idMcc).get();
        Integer idTrainer = employeeRepository.findById(idMcc).get().getIdTrainer().getIdEmployee();
        Employee trainer = employeeRepository.findById(idTrainer).get();
        Project project = projectRepository.findById(trainee.getIdProject().getIdProject()).get();
        Employee employee = employeeRepository.findById(idMcc).get();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(employee.getEmail());
        helper.setSubject("Progres Project Akhir");
        message.setText(String.format("Dear %s,"
                +"<br>"
                +"<br>"
                +"Progres project akhir kelompokmu dengan judul : <b>%s</b> DITOLAK."
                +"<br>"
                +"Untuk detail silakan mengunjungi laman history projectmu ya."
                +"<br>"
                +"Semangat!", employee.getName(), project.getTitle()), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
    public void notifValidasiDiterima (Integer idMcc) throws MessagingException{
        
        Trainee trainee = traineeRepository.findById(idMcc).get();
        Integer idTrainer = employeeRepository.findById(idMcc).get().getIdTrainer().getIdEmployee();
        Employee trainer = employeeRepository.findById(idTrainer).get();
        Project project = projectRepository.findById(trainee.getIdProject().getIdProject()).get();
        Employee employee = employeeRepository.findById(idMcc).get();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(employee.getEmail());
        helper.setSubject("Progres Project Akhir");
        message.setText(String.format("Dear %s,"
                +"<br>"
                +"<br>"
                +"Progres project akhir kelompokmu dengan judul : <b>%s</b> DITERIMA."
                +"<br>"
                +"Silakan mengunjungi laman My History dan melanjutkan ke tahap berikutnya."
                +"<br>"
                +"Semangat!", employee.getName(), project.getTitle()), "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
}
