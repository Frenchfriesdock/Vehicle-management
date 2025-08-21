package com.hosiky.sellers.demo;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFiller {

    /**
     * 按模板填充并输出新文件
     *
     * @param templatePath 模板路径（resources 下）
     * @param outPath      输出文件绝对路径
     * @param data         key -> value 映射
     */
    public static void fill(String templatePath, String outPath, Map<String, String> data) throws IOException {
        // 1. 读取模板
        try (InputStream is = new FileInputStream(templatePath)) {
            XWPFDocument doc = new XWPFDocument(is);

            // 2. 替换段落中的占位符
            for (XWPFParagraph p : doc.getParagraphs()) {
                replaceInParagraph(p, data);
            }

            // 3. 替换表格中的占位符（如有）
            for (XWPFTable table : doc.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            replaceInParagraph(p, data);
                        }
                    }
                }
            }

            // 4. 写出
            try (FileOutputStream fos = new FileOutputStream(outPath)) {
                doc.write(fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> data) {
        // 合并连续的 Runs，避免占位符被拆分
        mergeRuns(paragraph);

        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.text();
            if (text != null) {
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    text = text.replace("${" + entry.getKey() + "}", entry.getValue());
                }
                run.setText(text, 0);
            }
        }
    }

    /**
     * 简单合并段落内所有 Run，防止占位符被 Word 拆成多个 <w:r>
     */

    private static void mergeRuns(XWPFParagraph paragraph) {

        List<XWPFRun> runs = paragraph.getRuns();

        if (runs.size() <= 1) return;

        StringBuilder sb = new StringBuilder();
        for (XWPFRun r : runs) sb.append(r.text());

        for (int i = runs.size() - 1; i >= 0; i--) paragraph.removeRun(i);

        XWPFRun newRun = paragraph.createRun();
        newRun.setText(sb.toString());
        // 如需样式，可在此处设置
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("amount", "199.99");
        map.put("date", "2024-05-01 12:00");

        String template = "F:\\develop\\hosiky\\springcloud\\vehicle_management\\sellers-service\\src\\main\\resources\\templates\\templates.docx";
        String outFile   = "F:\\develop\\hosiky\\springcloud\\vehicle_management\\sellers-service\\src\\main\\resources\\OutTemplates\\OutFile.docx";
        fill(template, outFile, map);
        System.out.println("生成成功：" + outFile);
    }
}