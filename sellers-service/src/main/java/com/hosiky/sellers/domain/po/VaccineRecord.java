package com.hosiky.sellers.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("vaccine_record")
public class VaccineRecord {

    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime doseDate;
    private String vaccine;
}
