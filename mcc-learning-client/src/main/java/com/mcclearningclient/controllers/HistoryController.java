/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.HistoryDTO;
import com.mcclearningclient.services.HistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    
    //############## mapping untuk history trainee #################
    @GetMapping("/myHistory/{id}")
    public @ResponseBody List<HistoryDTO>  getmyHistory(@PathVariable Integer id) {
        System.out.println("=== GET HISTORY TRAINEE ===");
        return historyService.getHistoryByIdMcc(id);
    }
    
    @GetMapping("/my-history")
    public String getMyHistory(Model model) {
        Authentication authent = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("history", historyService.getHistoryByIdMcc(Integer.parseInt(authent.getName())));
        return "libraryProject/myHistory";
    }
    //##################################################################
    
}