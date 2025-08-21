package com.hosiky.sellers.utils;

import com.hosiky.sellers.domain.po.VaccineRecord;
import kotlin.Pair;

import java.util.List;

public class ExcelUtils {

    /**
     *
     *  Excel 名字
     *  表头
     *  数据库查询得到的数据 List<list<对象>>
     *  数据
     *
     *
     * 流式处理，分批插入或者导入数据
     */


    /**
     *
     * @param fileName
     * @param head
     * @param data
     *
     *
     *  1 给文件名（这个文件可以是一个模版）
     *
     *  2 输入表头
     *
     *  3 给出输入数据，从数据库里面查到的数据(查询返回的list对象数据)
     *
     *  4 分页查询，不一次查询，批量插入
     *
     *  5 流式输入， 一边生成一边输入进去 EasyExcel的流式输入，清理零时文件
     *
     *  6 映射表
     */
    public void exportExcel(String fileName, List<String> head, List<VaccineRecord> data, List<Pair<String,String>> T) {

    }


    /**
     *
     * @param fileName
     *
     * 1 数据模板
     *
     * 2 数据校验：数据校验有两种  逐行读取的数据存储在 list<object>
     * 2.1 字段长度、字段正则表达式校验等，内存内校验不存在外部数据交互。对性能影响较小
     * 2.2 数据重复性校验，如票据号是否和系统已存在的票据号重复(需要查询数据库，十分影响性能)
     *  解决思路：
     *
     *      利用EasyExcel进行Excel数据读取，因其逐行读取数据而非一次性加载整个文件至内存
     *
     *      借助EasyExcel的ReadListener进行数据处理。
     *
     *      定义一个BATCH_SIZE的大小，用于对数据的批量插入
     *
     * 3 数据插入：
     *
     * 每次读取一定数量的数据进行操作 eg 1000 批量插入
     */

    public void importExcel(String fileName) {

    }

}
