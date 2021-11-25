/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ModuleDTO {
    private Integer idModule;
    private String title;
    private byte[] file;
    private String category;
    private String createdAt;
    private Date updatedAt;
    private Integer idClass;

   
    
    
}
