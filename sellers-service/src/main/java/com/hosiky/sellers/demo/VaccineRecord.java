package com.hosiky.sellers.demo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("vaccine_record")
public class VaccineRecord {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime doseDate;
    private String vaccine;
}

