package com.alibaba.excel.main.a20191029;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(25)
public class Bean {
    @ExcelProperty(index = 0, value = "手机号码")
    public Long 手机号码;
}
