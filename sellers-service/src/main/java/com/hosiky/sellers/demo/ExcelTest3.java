package com.hosiky.sellers.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelTest3 {
    public static void main(String[] args) throws Exception {

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue(1);
        cell.setCellValue(4);

        /**
         *  样式种类
         */
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);

        try(OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}
