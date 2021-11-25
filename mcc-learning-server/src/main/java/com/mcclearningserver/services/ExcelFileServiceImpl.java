/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.services;

import com.mcclearningserver.entities.Attendance;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO-KL
 */
@Service
public class ExcelFileServiceImpl implements ExcelFileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ByteArrayInputStream export(List<Attendance> attendance) {
        try (Workbook workbook = new XSSFWorkbook()) {
            CreationHelper creationHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Attendance");

            Row row = sheet.createRow(0);

            // Define header cell style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle datestyle = workbook.createCellStyle();
            datestyle.setDataFormat(creationHelper.createDataFormat().getFormat("mm/dd/yyyy"));

            Cell cell = row.createCell(0);
            cell.setCellValue("ID employee");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Employee Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Date");
            cell.setCellStyle(datestyle);

            cell = row.createCell(3);
            cell.setCellValue("Status");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Note");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Verified");
            cell.setCellStyle(headerCellStyle);

            // Creating data rows for each attendance
            for (int i = 0; i < attendance.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(attendance.get(i).getIdEmployee().getIdEmployee());
                dataRow.createCell(1).setCellValue(attendance.get(i).getIdEmployee().getName());
                Cell cell1 = dataRow.createCell(2);
                cell1.setCellValue(attendance.get(i).getDate());
                CellStyle style1 = workbook.createCellStyle();
                style1.setDataFormat(creationHelper.createDataFormat().getFormat(
                        "dd-mm-yyyy"));
                cell1.setCellStyle(style1);
                dataRow.createCell(3).setCellValue(attendance.get(i).getStatus());
                dataRow.createCell(4).setCellValue(attendance.get(i).getNote());
                dataRow.createCell(5).setCellValue(attendance.get(i).getIsVerified());
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }
}
