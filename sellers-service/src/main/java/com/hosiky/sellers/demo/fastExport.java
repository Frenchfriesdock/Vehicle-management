package com.hosiky.sellers.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.*;

public class fastExport {

    // 常量
    public static final int TOTAL_ROWS = 1_000_000; // 总数据
    public static final int BATCH_SIZE = 200_000;   // 每批行数

    public static void main(String[] args) {
        String fileName = "big.xlsx";

        // 表头
        List<List<String>> head = Arrays.asList(
                Arrays.asList("id"),
                Arrays.asList("name"),
                Arrays.asList("age"),
                Arrays.asList("dose_date"),
                Arrays.asList("vaccine")
        );

        try (ExcelWriter writer = EasyExcel.write(fileName).head(head).build()) {
            int sheetNo = 0; // Sheet 序号，从 0 开始
            for (int offset = 0; offset < TOTAL_ROWS; offset += BATCH_SIZE) {

                // 构造当前批次数据
                List<List<String>> batch = new ArrayList<>();
                for (int j = 0; j < BATCH_SIZE && offset + j < TOTAL_ROWS; j++) {
                    long id = offset + j;
                    batch.add(Arrays.asList(
                            String.valueOf(id),
                            "name-" + id,
                            String.valueOf(id % 100),
                            "2024-06-" + (id % 28 + 1) + " 10:00:00",
                            "vaccine-" + (id % 10)
                    ));
                }

                // 创建并写入当前 Sheet
                WriteSheet sheet = EasyExcel
                        .writerSheet(sheetNo, "sheet-" + (sheetNo + 1))
                        .build();
                writer.write(batch, sheet);
                sheetNo++;
            }
        }

        System.out.println("✅ 导出完成！文件：" + new File(fileName).getAbsolutePath());
    }
}