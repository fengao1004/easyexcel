package com.alibaba.excel.main.a20191017;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class OutBean {
    @ExcelProperty("子模板编号")
    public String 子模板编号;
    @ExcelProperty("子模板描述")
    public String 子模板描述;
    @ExcelProperty("评审维度")
    public String 评审维度;
    @ExcelProperty("评审维度描述")
    public String 评审维度描述;
    @ExcelProperty("评审指标")
    public String 评审指标;
    @ExcelProperty("评审指标描述")
    public String 评审指标描述;
    @ExcelProperty("评审标准描述")
    public String 评审标准描述;
    @ExcelProperty("部门")
    public String 部门;
    @ExcelProperty("部门描述")
    public String 部门描述;
    @ExcelProperty("权重")
    public String 权重;
    @ExcelProperty("分值")
    public String 分值;

    public OutBean(Bean bean) {
        评审维度描述 = bean.评估维度;
        部门描述 = bean.评价部门;
        权重 = bean.分值 + "%";
        分值 = bean.分值;
        评审标准描述 = bean.评分标准;
        评审指标描述 = bean.评价指标;
    }
}
