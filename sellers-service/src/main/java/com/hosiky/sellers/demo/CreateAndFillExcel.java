package com.hosiky.sellers.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreateAndFillExcel {

    public static void main(String[] args) {
        // 1. 创建工作簿 & 工作表
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("订单");

        // 2. 准备表头样式
        CellStyle headStyle = wb.createCellStyle();
        Font headFont = wb.createFont();
        headFont.setBold(true);
        headStyle.setFont(headFont);
        headStyle.setAlignment(HorizontalAlignment.CENTER);

        // 3. 写表头
        Row headRow = sheet.createRow(0);
        List<String> headers = Arrays.asList("ID", "客户", "金额");
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headStyle);
        }

        // 4. 写数据
        List<Order> data = List.of(
                new Order(1, "张三", 199.99),
                new Order(2, "李四", 299.99)
        );
        for (int i = 0; i < data.size(); i++) {
            Order o = data.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(o.id);
            row.createCell(1).setCellValue(o.customer);
            row.createCell(2).setCellValue(o.amount);
        }

        // 5. 自动列宽
        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // 6. 写出文件
        try (FileOutputStream fos = new FileOutputStream("output_orders.xlsx")) {
            wb.write(fos);
            System.out.println("Excel 已生成：output_orders.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 7. 关闭资源
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 简单 POJO
    static class Order {
        int id;
        String customer;
        double amount;

        Order(int id, String customer, double amount) {
            this.id = id;
            this.customer = customer;
            this.amount = amount;
        }
    }
}