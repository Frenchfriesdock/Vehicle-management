package com.hosiky.common.entity.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 车商账号状态枚举
 *  同时也可以用到buyer上面
 *  代码的复用
 */
@Getter
public enum SellerStatusEnum implements IEnum<String> {
    ACTIVE("active", "活跃"),
    INACTIVE("inactive", "不活跃");

    @EnumValue
    private final String code;
    private final String desc;

    // 必须显式定义两参构造
    SellerStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return code;
    }
}