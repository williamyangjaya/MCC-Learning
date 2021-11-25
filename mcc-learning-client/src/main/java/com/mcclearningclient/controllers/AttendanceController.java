/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningclient.controllers;

import com.mcclearningclient.models.Attendance;
import com.mcclearningclient.models.AttendanceByBatchDTO;
import com.mcclearningclient.models.AttendanceDTO;
import com.mcclearningclient.services.AttendanceService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author William Yangjaya
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping
    public String viewAttendance() {
        return "attendance";
    }

    @GetMapping("/get-all")
    public @ResponseBody
    List<Attendance> getAllAttendance() {
        return attendanceService.listAttendance();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Attendance getById(@PathVariable("id") Integer id) {
        return attendanceService.attendanceById(id);
    }

    // getByBatchAndDate -> untuk trainer pertama dulu
    @PostMapping("/getAttendance")
    public @ResponseBody
    List<Attendance> attendanceByBatch(@RequestBody AttendanceByBatchDTO batchDTO) {
        return attendanceService.attendanceByBatch(batchDTO);
    }

    // Done
    @GetMapping("/get/{idEmployee}")
    public @ResponseBody
    List<Attendance> attendanceByEmpId(@PathVariable Integer idEmployee) {
        return attendanceService.attendanceByEmpId(idEmployee);
    }

    @GetMapping("/daily/{date}")
    public Attendance[] getByDate(@PathVariable String date) throws ParseException {
        return attendanceService.getByDate(date);
    }

    // Done
    @PostMapping
    public @ResponseBody
    Attendance insertAttendance(@RequestBody Attendance attendance) {
        return attendanceService.insertAttendance(attendance);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Attendance update(@PathVariable("id") Integer id, @RequestBody Attendance attendance) {
        return attendanceService.update(id, attendance);
    }

    //update verify for trainer
    @PutMapping("/verify/{id}")
    public @ResponseBody
    Attendance updateVerify(@PathVariable("id") Integer id, @RequestParam boolean isVerified) {
        return attendanceService.updateVerify(id, isVerified);
    }

    //export by id and between two date with request DTO
    //used requestBody
    //ex.  body { "idEmployee":20, "startDate": "2021-06-21", "endDate": "2021-06-25"}
    @PostMapping(value = "/downloadExcelFile")
    public @ResponseBody
    boolean reportAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        boolean result = attendanceService.exportAllAttendance(attendanceDTO);
        if (result == true) {
            try {
                Process builder = Runtime.getRuntime().exec("cmd /c start D:/Data/Downloads");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
