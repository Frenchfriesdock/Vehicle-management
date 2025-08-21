package com.hosiky.sellers.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class ExcelTest1 {
    public static void main(String[] args) {


        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();

        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet = wb.createSheet("hello sheet");

        Row row = sheet1.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("1");

        row.createCell(1).setCellValue(1.2);
//        输入一个string类型
        row.createCell(2).setCellValue(creationHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        row.createCell(4).setCellValue(new Date());

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                creationHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss")
        );

        row.createCell(5).setCellValue(new Date());
        row.createCell(5).setCellStyle(cellStyle);

        row.createCell(6).setCellValue(Calendar.getInstance());
        row.createCell(6).setCellStyle(cellStyle);


        Row row1 = sheet1.createRow(1);
        Cell cell1 = row1.createCell(0);

        cell1.setCellValue("1");
        row1.createCell(1).setCellValue(new Date());
        row1.getCell(1).setCellStyle(cellStyle);


        row1.createCell(2).setCellValue(Calendar.getInstance());
        row1.createCell(3).setCellValue("a String");
        row1.createCell(4).setCellValue(true);
        row1.createCell(5).setCellType(CellType.ERROR);

        Cell cell2 = row1.createCell(6);
        cell2.setCellValue(Calendar.getInstance());
        cell2.setCellStyle(cellStyle);


        row1.createCell(7, CellType.NUMERIC).setCellValue(new Date());
        row1.getCell(7).setCellStyle(cellStyle);


        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            
            wb.write(fileOut);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
