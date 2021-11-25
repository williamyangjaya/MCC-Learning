/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.AuthDTO;
import com.mcclearningserver.dto.HistoryDTO;
import com.mcclearningserver.services.HistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    
    //dear aul, ini sama persis yak sama project yg sebelumnya
    
    @GetMapping("/employee/{id}")
    public List<HistoryDTO> getHistoryByIdMcc(@PathVariable Integer id){
        System.out.println("cetak auth history");
        return historyService.getHistoryByIdMcc(id);
    }
}
