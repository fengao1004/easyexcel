package com.alibaba.excel.main.a20191017;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
public class Bean {
    @ExcelProperty(index = 0)
    public   String 评估维度;
    @ExcelProperty(index = 1)
    public   String 评价指标;
    @ExcelProperty(index = 2)
    public   String 分值;
    @ExcelProperty(index = 3)
    public   String 评分标准;
    @ExcelProperty(index = 4)
    public   String 得分;
    @ExcelProperty(index = 5)
    public   String 权重;
    @ExcelProperty(index = 6)
    public   String 评价人;
    @ExcelProperty(index = 7)
    public   String 评价部门;
}
