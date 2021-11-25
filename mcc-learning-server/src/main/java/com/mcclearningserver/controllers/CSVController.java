/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO-KL
 */
@RestController
@RequestMapping("/attendance")
public class CSVController {

    @Autowired
    CSVService cSVService;

    @GetMapping("/download/{idEmployee}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer idEmployee) {
        String filename = "attendance" + idEmployee + ".csv";
        InputStreamResource file = new InputStreamResource(cSVService.load(idEmployee));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}
