package com.alibaba.excel.main.statisticalA;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DateBean {

    @ExcelProperty(value = {"供应商名称"}, index = 1)
    public String companyNmae;


    @ExcelProperty(value = {"城市公司"}, index =4)
    public String cityName;

    @ExcelProperty(value = {"合同名称"}, index = 8)
    public String projectNmae;
}
