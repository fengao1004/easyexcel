package com.alibaba.excel.main.a20191017;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class OutBean2 {
    @ExcelProperty("类")
    public String 类;

    public OutBean2(String 子模板编号, String 子模板描述, String 评审维度, String 评审维度描述) {
        this.子模板编号 = 子模板编号;
        this.子模板描述 = 子模板描述;
        this.评审维度 = 评审维度;
        this.评审维度描述 = 评审维度描述;
    }

    @ExcelProperty("子模板编号")
    public String 子模板编号;
    @ExcelProperty("子模板描述")
    public String 子模板描述;
    @ExcelProperty("评审维度")
    public String 评审维度;
    @ExcelProperty("评审维度描述")
    public String 评审维度描述;
}
