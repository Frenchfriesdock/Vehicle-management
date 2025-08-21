package com.hosiky.sellers.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.util.Iterator;

public class ExcelReadDemo {

    public static void main(String[] args) throws Exception {
        // 1. 支持 .xls 和 .xlsx，自动识别
        File file = new File("text.xlsx");   // 换成你的文件名
        Workbook wb = WorkbookFactory.create(file);

        // 2. 遍历所有工作表
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            System.out.println("===== 工作表：" + sheet.getSheetName() + " =====");

            // 3. 遍历所有行
            for (Row row : sheet) {
                // 4. 遍历所有列
                for (Cell cell : row) {
                    String value = getCellValueAsString(cell);
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        }

        wb.close();
    }

    /**
     * 把任何类型的单元格统一转成字符串
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // 判断是否为日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                // 防止科学计数法
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // 公式结果
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
                return FormulaError.forInt(cell.getErrorCellValue()).getString();
            default:
                return "未知类型";
        }
    }
}