package com.hosiky.common.entity.Enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements IEnum<String> {

    UNPAID("unpaid", "未支付"),
    PAID("paid", "已支付"),
    SHIPPED("shipped", "已发货"),
    DONE("done", "已完成"),
    CANCEL("cancel", "已取消");

    @EnumValue
    private String code;
    private String desc;

    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return code;
    }
}