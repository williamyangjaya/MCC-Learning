/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.FeedbackDTO;
import com.mcclearningserver.entities.Feedback;
import com.mcclearningserver.services.FeedbackService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/feedback-form")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping("")
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/{category}")
    public Feedback getFormByCategory(@PathVariable String category) {
        return feedbackService.getFormByCategory(category);
    }

    @PostMapping("")
    public Feedback insertForm(@RequestBody Feedback feedback) {
        return feedbackService.insertForm(feedback);
    }

    @PutMapping("/{category}")
    public Feedback updateFormByCategory(@PathVariable String category, @RequestParam String link) {
        return feedbackService.updateFormByCategory(link, category);
    }
}
