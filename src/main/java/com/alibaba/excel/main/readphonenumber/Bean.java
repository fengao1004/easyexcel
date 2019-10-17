package com.alibaba.excel.main.readphonenumber;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Bean {
    @ExcelProperty(value = {"合同名称"}, index = 1)
    public String phoneNumber;
}
