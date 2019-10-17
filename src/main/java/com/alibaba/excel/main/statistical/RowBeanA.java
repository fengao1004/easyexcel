package com.alibaba.excel.main.statistical;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(45)
@Data
public class RowBeanA implements Comparable<RowBeanA> {
    @ColumnWidth(18)
    @ExcelProperty(value = {"合作模式"}, index = 0)
    public String teamworkModel;
    @ColumnWidth(18)
    @ExcelProperty(value = {"合同数量"}, index = 1)
    public String contractNum;
    @ColumnWidth(18)
    @ExcelProperty(value = {"公司数量"}, index = 2)
    public Integer companyNum;

    @ExcelProperty(value = {"供应商名称"}, index = 3)
    public String name;
    @ColumnWidth(18)
    @ExcelProperty(value = {"城市名称"}, index = 4)
    public String cityName;
    @ColumnWidth(80)
    @ExcelProperty(value = {"合同名称"}, index = 6)
    public String htong;
    @ColumnWidth(18)
    @ExcelProperty(value = {"同城市合同数量"}, index = 5)
    public Integer cityhrtongnum;

    @ExcelIgnore
    public Integer cityNum;

    @ExcelIgnore
    public Integer contractNumInt;

    @ExcelIgnore
    public Integer total;

    @Override
    public int compareTo(RowBeanA o) {
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

    public RowBeanA createCopy() {
        RowBeanA rowBean1 = new RowBeanA();
        rowBean1.teamworkModel = this.teamworkModel;
        rowBean1.contractNum = this.contractNum;
        rowBean1.name = this.name;
        rowBean1.cityNum = this.cityNum;
        rowBean1.contractNumInt = this.contractNumInt;
        return rowBean1;
    }
}
