package com.hosiky.sellers.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillTemplateExcel {

    private static final Pattern PLACEHOLDER = Pattern.compile("#\\{(.*?)}");

    public static void main(String[] args) throws IOException {
        // 1. 读取模板
        String templatePath = "F:\\develop\\hosiky\\springcloud\\vehicle_management\\sellers-service\\src\\main\\resources\\templates\\templates.xlsx";
        InputStream is = new FileInputStream(templatePath);
//        InputStream is = FillTemplateExcel.class
//                .getClassLoader()
//                .getResourceAsStream("templates/template.xlsx");

        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);

        // 2. 准备填充数据
        Map<String, Object> map = new HashMap<>();
        map.put("customer", "张三");
        map.put("amount", 199.99);
        map.put("date", "2024-05-01");

        // 3. 遍历所有单元格并替换占位符
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    String value = cell.getStringCellValue();
                    Matcher m = PLACEHOLDER.matcher(value);
                    StringBuffer sb = new StringBuffer();
                    while (m.find()) {
                        String key = m.group(1);
                        Object replaceVal = map.getOrDefault(key, "");
                        m.appendReplacement(sb, Matcher.quoteReplacement(replaceVal.toString()));
                    }
                    m.appendTail(sb);
                    cell.setCellValue(sb.toString());
                }
            }
        }

        // 4. 写出
        try (FileOutputStream fos = new FileOutputStream("filled_template.xlsx")) {
            wb.write(fos);
            System.out.println("模板已填充完成：filled_template.xlsx");
        }

        wb.close();
        is.close();
    }
}