package com.alibaba.excel.main.statistical;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@ContentRowHeight(50)
@HeadRowHeight(20)
@ColumnWidth(45)
@Data
public class RowBean implements Comparable<RowBean> {
    @ColumnWidth(12)
    @ExcelProperty(value = {"合作模式"}, index = 0)
    public String teamworkModel;
    @ColumnWidth(12)
    @ExcelProperty(value = {"合同数量"}, index = 1)
    public String contractNum;

    @ExcelProperty(value = {"公司名称"}, index = 2)
    public String name;

    @ExcelProperty(value = {"青岛"}, index = 3)
    public String qd;

    @ExcelProperty(value = {"济南"}, index = 4)
    public String jn;

    @ExcelProperty(value = {"石家庄"}, index = 5)
    public String sjz;

    @ExcelProperty(value = {"太原"}, index = 6)
    public String taiyuan;

    @ExcelProperty(value = {"烟威龙"}, index = 7)
    public String yanweilong;

    @ExcelProperty(value = {"北京"}, index = 8)
    public String beijing;

    @ExcelProperty(value = {"环京"}, index = 9)
    public String huanjing;

    @ExcelProperty(value = {"兰州"}, index = 10)
    public String lanzhou;

    @ExcelProperty(value = {"小计"}, index = 11)
    public Integer total;

    @ExcelIgnore
    public Integer cityNum;

    @ExcelIgnore
    public Integer contractNumInt;

    @Override
    public int compareTo(RowBean o) {
        if (o.cityNum > cityNum) {
            return -1;
        }
        if (o.cityNum.equals(cityNum)) {
            if (o.contractNumInt > contractNumInt) {
                return -1;
            }
        }
        return 1;
    }


}
