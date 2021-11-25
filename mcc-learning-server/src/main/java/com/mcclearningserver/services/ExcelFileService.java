/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.entities.Attendance;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 *
 * @author LENOVO-KL
 */
public interface ExcelFileService {

    ByteArrayInputStream export(List<Attendance> attendance);

}
