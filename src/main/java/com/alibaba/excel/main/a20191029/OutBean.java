package com.alibaba.excel.main.a20191029;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(25)
public class OutBean {
    public OutBean(String 手机号码) {
        this.手机号码 = 手机号码;
    }

    @ExcelProperty(index = 0, value = "序号")
    public Integer 序号;
    @ExcelProperty(index = 1, value = "手机号码")
    public String 手机号码;
}
