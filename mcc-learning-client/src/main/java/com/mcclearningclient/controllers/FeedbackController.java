/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.FeedbackForm;
import com.mcclearningclient.models.FeedbackUpdateDTO;
import com.mcclearningclient.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ROG
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    
    @Autowired
    FeedbackService feedbackService;
    
    @GetMapping("")
    public String feedbackPage(Model model) {
        model.addAttribute("feedback", feedbackService.getFeedback());
        return "feedback/feedback";
    }
    
    @GetMapping("/{category}")
    public @ResponseBody FeedbackForm getFeedbackByCategory(@PathVariable String category) {
        return feedbackService.getFeedbackByCategory(category);
    }
    
    @PutMapping("/update/{category}")
    public @ResponseBody FeedbackUpdateDTO updateFeedback(@PathVariable("category") String category, String link) {
        return feedbackService.updateFeedback(category, link);
    }

}
