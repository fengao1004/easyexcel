package com.alibaba.excel.main.statisticalA;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(12)
@Data
public class RowBean implements Comparable<RowBean> {
    @ColumnWidth(12)
    @ExcelProperty(value = {"合作模式"}, index = 0)
    public String teamworkModel;

    @ExcelProperty(value = {"青岛公司"}, index = 1)
    public Integer qd = 0;

    @ExcelProperty(value = {"济南公司"}, index = 2)
    public Integer jn = 0;

    @ExcelProperty(value = {"石家庄公司"}, index = 3)
    public Integer sjz = 0;

    @ExcelProperty(value = {"太原公司"}, index = 4)
    public Integer taiyuan = 0;

    @ExcelProperty(value = {"烟威龙公司"}, index = 5)
    public Integer yanweilong = 0;

    @ExcelProperty(value = {"北京公司"}, index = 6)
    public Integer beijing = 0;

    @ExcelProperty(value = {"环京公司"}, index = 7)
    public Integer huanjing = 0;

    @ExcelProperty(value = {"兰州公司"}, index = 8)
    public Integer lanzhou = 0;

    @ExcelProperty(value = {"小计公司"}, index = 9)
    public Integer total = 0;

    @ExcelIgnore
    public Integer cityNum;

    @Override
    public int compareTo(RowBean o) {
        if (o.cityNum > cityNum) {
            return -1;
        }
        return 1;
    }


}
