package com.alibaba.excel.main.a20191104;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(25)
public class Bean2 {
    @ExcelProperty(index=0,value="产品编码")
    public   String 产品编码;
    @ExcelProperty(index=1,value="手机号码")
    public   String 手机号码;
    @ExcelProperty(index=2,value="终端品牌")
    public   String 终端品牌;
    @ExcelProperty(index=3,value="终端型号")
    public   String 终端型号;
}
