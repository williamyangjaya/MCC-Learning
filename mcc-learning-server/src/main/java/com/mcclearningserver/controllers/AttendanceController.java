/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.controllers;

import com.mcclearningserver.dto.AttendanceByBatchDTO;
import com.mcclearningserver.dto.AttendanceDTO;
import com.mcclearningserver.dto.AuthDTO;
import com.mcclearningserver.entities.Attendance;
import com.mcclearningserver.repositories.AttendanceRepository;
import com.mcclearningserver.repositories.EmployeeRepository;
import com.mcclearningserver.services.AttendanceService;
import com.mcclearningserver.services.ExcelFileService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO-KL
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    private ExcelFileService excelFileService;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    //get All
    @GetMapping("")
    public ResponseEntity<List<Attendance>> listAttendance() {
        return new ResponseEntity<>(attendanceService.getAll(), HttpStatus.OK);
    }

    //get All by Id Attendance
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> attendanceById(@PathVariable Integer id) {
        return new ResponseEntity<>(attendanceService.getById(id), HttpStatus.OK);
    }

    //get All by Batch and Date
    @PostMapping("/getAttendance")
    public List<Attendance> attendanceByBatch(@RequestBody AttendanceByBatchDTO batchDTO) throws ParseException {
        return attendanceService.getAttendanceByBatch(batchDTO);
    }

    //get All by Employee Id
    @GetMapping("/get/{idEmployee}")
    public ResponseEntity<List<Attendance>> attendanceByEmpId(@PathVariable Integer idEmployee) {
        return new ResponseEntity<>(attendanceService.getByIdEmp(idEmployee), HttpStatus.OK);
    }

    //get All by Auth DTO 
    @PostMapping("/user")
    public ResponseEntity<List<Attendance>> attendanceByAuth(@RequestBody AuthDTO authDTO) {
        return new ResponseEntity<>(attendanceService.getAttendByIdEmp(authDTO.getUsername()), HttpStatus.OK);
    }

    //get All by date 
    @GetMapping("/daily/{date}")
    public ResponseEntity<List<Attendance>> getByDate(@PathVariable String date) throws ParseException {
        Date dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return new ResponseEntity<>(attendanceService.getAttendByDate(dateFormat), HttpStatus.OK);
    }

    //create attendance
    @PostMapping("")
    public Attendance insertAttendance(@RequestBody Attendance attendance) throws MessagingException {
        return attendanceService.create(attendance);
    }

    //update all data attendance
    @PutMapping("/{id}")
    public Attendance update(@PathVariable Integer id, @RequestBody Attendance attendance) throws MessagingException {
        return attendanceService.update(id, attendance);
    }

    //update verify for trainer
    @PutMapping("/verify/{id}")
    public Attendance updateVerify(@PathVariable Integer id, @RequestParam boolean isVerified) throws MessagingException {
        return attendanceService.updateVerify(id, isVerified);
    }

    //export by id and between two date 
    //used requestParam
    @GetMapping(value = "/downloadFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void reportAll(@RequestParam("idEmployee") Integer idEmployee, @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate, HttpServletResponse response) throws IOException, ParseException {

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = parser.parse(fromDate);
        Date endDate = parser.parse(toDate);

        List<Attendance> attendances = attendanceService.exportAll(idEmployee, startDate, endDate);
        ByteArrayInputStream in = excelFileService.export(attendances);
        IOUtils.copy(in, response.getOutputStream());
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=attendance.xlsx");
        response.flushBuffer();
    }

    //export by id and between two date with request DTO
    //used requestBody
    @PostMapping(value = "/downloadExcelFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] reportAttendance(@RequestBody AttendanceDTO attendanceDTO, HttpServletResponse response) throws IOException, ParseException {

        List<Attendance> attendances = attendanceService.exportAllAttendance(attendanceDTO);
        System.out.println(attendanceDTO);

        ByteArrayInputStream in = excelFileService.export(attendances);
        IOUtils.copy(in, response.getOutputStream());
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=attendance.xlsx");
        response.flushBuffer();
        return IOUtils.toByteArray(in);
    }

}
