/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.entities.Attendance;
import com.mcclearningserver.repositories.AttendanceRepository;
import com.mcclearningserver.repositories.EmployeeRepository;
import helper.CSVHelper;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO-KL
 */
@Service
public class CSVService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ByteArrayInputStream load(Integer idEmployee) {

        List<Attendance> attendance = employeeRepository.findById(idEmployee).get().getAttendanceList();
        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(attendance);
        return in;
    }

}
