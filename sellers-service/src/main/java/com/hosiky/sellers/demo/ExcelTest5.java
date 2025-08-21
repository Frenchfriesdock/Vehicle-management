package com.hosiky.sellers.demo;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ExcelTest5 {
    public static void main(String[] args) throws Exception {
        try(InputStream inp = new FileInputStream("workbook.xls")){

            /**
             * 把已存在的 Excel 文件 workbook.xls 第 3 行（下标 2）第 4 列（下标 3）的单元格内容改为字符串 “a test”，
             * 然后把修改后的结果另存为 workbook2.xlsx。
             */
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(3);
            if(cell == null) {
                cell = row.createCell(3);
            }
            cell.setCellType(CellType.STRING);
            cell.setCellValue("a test");

            try(OutputStream out = new FileOutputStream("workbook2.xlsx")){
                wb.write(out);
            }
        }
    }

}
