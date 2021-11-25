/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ROG
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserQuizDto {
    private Integer idClass;
    private Integer idEmployee;
    private String category;
    private Integer idModule;
}
