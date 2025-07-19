package com.hosiky.common.entity.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum CarStatusEnum implements IEnum<String> {

    ONSALE("onsale","在售"),
    SOLD("sold","已售"),
    OFF("off","下架");

    @EnumValue
    private String code;
    private String desc;


     CarStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String getValue() {
        return code;
    }
}
