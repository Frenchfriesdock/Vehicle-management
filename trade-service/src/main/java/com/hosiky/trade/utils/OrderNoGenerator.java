package com.hosiky.trade.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 订单号生成器
 * 格式：yyyyMMddHHmmss + 雪花ID
 * 示例：202507261529301234567890
 */
public final class OrderNoGenerator {

    // 数据中心ID + 机器ID（单机可写死）
    private static final Snowflake SNOW_FLAKE = IdUtil.getSnowflake(1, 1);

    private OrderNoGenerator() {
        // 防止实例化
    }

    /**
     * 生成全局唯一订单号
     */
    public static String generate() {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + SNOW_FLAKE.nextIdStr();
    }
}