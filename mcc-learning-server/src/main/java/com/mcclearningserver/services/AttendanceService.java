/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.dto.AttendanceByBatchDTO;
import com.mcclearningserver.dto.AttendanceDTO;
import com.mcclearningserver.entities.Attendance;
import com.mcclearningserver.entities.Employee;
import com.mcclearningserver.entities.Trainee;

import com.mcclearningserver.entities.User;
import com.mcclearningserver.repositories.AttendanceRepository;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.repositories.TraineeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO-KL
 */
@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    LoginService loginService;

    //getAll
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    //get By Id Attendance
    public Attendance getById(Integer id) {
        Optional<Attendance> optional = attendanceRepository.findById(id);
        Attendance attendance = null;
        if (optional.isPresent()) {
            attendance = optional.get();
        } else {
            throw new RuntimeException(" attendance not found for id :: " + id);
        }
        return attendance;
    }

    //getAll by EmployeeId
    public List<Attendance> getByIdEmp(Integer idEmployee) {
        List<Attendance> attendances = employeeRepository.findById(idEmployee).get().getAttendanceList();
        return attendances;

    }

    //getAll by Batch 
    public List<Attendance> getAttendanceByBatch(AttendanceByBatchDTO attendanceByBatchDTO) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parser.parse(attendanceByBatchDTO.getDate());
        List<Attendance> attendances = attendanceRepository.findAllbyBatch(
                attendanceByBatchDTO.getBatch(), date);
        return attendances;
    }

    //get All by Username Auth
    public List<Attendance> getAttendByIdEmp(String userName) {
        User user = loginService.loadUserByUsername(userName);
        List<Attendance> attendances = employeeRepository.findById(user.getIdUser()).get().getAttendanceList();
        return attendances;
    }

    //get All by Date
    //for Trainer
    public List<Attendance> getAttendByDate(Date date) {
        return (List<Attendance>) attendanceRepository.findByDate(date);
    }

    //create attendance 
    public Attendance create(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    //update all attendance
    public Attendance update(Integer id, Attendance attendance) {
        Attendance updateattend = attendanceRepository.findById(id).get();
        updateattend.setDate(attendance.getDate());
        updateattend.setCreatedAt(attendance.getCreatedAt());
        updateattend.setStatus(attendance.getStatus());
        updateattend.setNote(attendance.getNote());
        updateattend.setIsVerified(attendance.getIsVerified());
        updateattend.setIdEmployee(attendance.getIdEmployee());
        return attendanceRepository.save(updateattend);
    }

    //update verify 
    //for trainer
    public Attendance updateVerify(Integer idAttendance, boolean isVerified) {
        Attendance attendance = attendanceRepository.findById(idAttendance).get();
        attendance.setIsVerified(isVerified);
        return attendanceRepository.save(attendance);
    }

    //export with two date 
    public List<Attendance> exportAllAttendanceByDate(Date startDate, Date endDate) {
        return attendanceRepository.findByRequestReceivedDateBetween(startDate, endDate);
    }

    //export by id with two date 
    public List<Attendance> exportAll(Integer idEmployee, Date startDate, Date endDate) {
        return attendanceRepository.findAllbyIdDate(idEmployee, startDate, endDate);
    }

    //export by id and Date with DTO
    public List<Attendance> exportAllAttendance(AttendanceDTO attendanceDTO) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = parser.parse(attendanceDTO.getStartDate());
        Date endDate = parser.parse(attendanceDTO.getEndDate());

        return attendanceRepository.findAllbyIdDate(attendanceDTO.getIdEmployee(), startDate, endDate);

    }
}
