/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.models;

import java.util.Date;

/**
 *
 * @author ROG
 */
public class Answer {
    private Integer idAnswer;
    private byte[] link;
    private Date createdAt;
    private String fileName;
    private String type;
    private Employee idEmployee;
}
