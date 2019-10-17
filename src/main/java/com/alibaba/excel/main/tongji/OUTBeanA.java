package com.alibaba.excel.main.tongji;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@ColumnWidth(12)
@HeadRowHeight(22)
@ContentRowHeight(15)
@Data
public class OUTBeanA {
    @ExcelProperty("城市名称")
    String cityName;
    @ExcelProperty("总包")
    Integer zb=0;
    @ExcelProperty("精装")
    Integer jz=0;
    @ExcelProperty("景观")
    Integer jg=0;
    @ExcelProperty("门窗")
    Integer mc=0;
    @ExcelProperty("幕墙")
    Integer mq=0;


    public OUTBeanA(String s) {
        this.cityName = s;
    }
}
