package com.hosiky.sellers.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VaccineRecordMapper extends BaseMapper<VaccineRecord> {
    // 批量插入
    @Insert({
        "<script>",
        "INSERT INTO vaccine_record(name,age,dose_date,vaccine) VALUES",
        "<foreach collection='list' item='r' separator=','>",
        "(#{r.name}, #{r.age}, #{r.doseDate}, #{r.vaccine})",
        "</foreach>",
        "</script>"
    })
    int batchInsert(@Param("list") List<VaccineRecord> list);
}
