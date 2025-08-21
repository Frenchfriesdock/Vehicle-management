package com.hosiky.sellers.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hosiky.sellers.utils.JdbcUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelImportMain {

    public static void main(String[] args) {
        String fileName = "big.xlsx";   // 要导入的文件
        SqlSessionFactory factory = JdbcUtil.sessionFactory();
        try (SqlSession session = factory.openSession(ExecutorType.BATCH, false)) {
            VaccineRecordMapper mapper = session.getMapper(VaccineRecordMapper.class);

            // 1. EasyExcel 监听器：每 1 w 行批量插入
            AnalysisEventListener<VaccineRecord> listener =
                new AnalysisEventListener<VaccineRecord>() {
                    private static final int BATCH = 10_000;
                    private final List<VaccineRecord> buffer = new ArrayList<>(BATCH);

                    @Override
                    public void invoke(VaccineRecord data, AnalysisContext ctx) {
                        buffer.add(data);
                        if (buffer.size() >= BATCH) {
                            flush(mapper, session);
                        }
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext ctx) {
                        flush(mapper, session);
                    }

                    private void flush(VaccineRecordMapper mapper, SqlSession session) {
                        if (!buffer.isEmpty()) {
                            mapper.batchInsert(buffer);
                            session.commit();      // 批量提交
                            buffer.clear();
                        }
                    }
                };

            // 2. 开始读取
            EasyExcel.read(fileName, VaccineRecord.class, listener)
                     .sheet()          // 第 1 个 Sheet
                     .doRead();        // 阻塞读完
            System.out.println("✅ 导入完成！");
        }
    }
}