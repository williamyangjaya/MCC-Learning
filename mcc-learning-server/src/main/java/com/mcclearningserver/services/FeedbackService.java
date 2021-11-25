/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.FeedbackDTO;
import com.mcclearningserver.entities.Feedback;
import com.mcclearningserver.repositories.FeedbackRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class FeedbackService {
    
    @Autowired
    FeedbackRepository feedbackRepository;
    
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedback = feedbackRepository.findAll();
        List<FeedbackDTO> getFeedback = new ArrayList<>();
        for (Feedback f : feedback) {
            FeedbackDTO dtos = new FeedbackDTO(
                    f.getIdForm(),
                    f.getLink(),
                    f.getCategory(),
                    f.getCreatedAt(),
                    f.getUpdatedAt());
            getFeedback.add(dtos);
        }
        return getFeedback;
    }
    
    public Feedback getFormById(Integer id) {
        return feedbackRepository.findById(id).get();
    }
    
    public Feedback getFormByCategory(String category) {
        return feedbackRepository.findByCategory(category);
    }
    
    public Feedback insertForm(Feedback feedback) {
        feedback.setUpdatedAt(new Date());
        return feedbackRepository.save(feedback);
    }
    
    public Feedback updateFormByCategory(String link, String category) {
        Feedback feedback = feedbackRepository.findByCategory(category);
        feedback.setLink(link);
        return feedbackRepository.save(feedback);
    }
}
