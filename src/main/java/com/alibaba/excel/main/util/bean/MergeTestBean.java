package com.alibaba.excel.main.util.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(12)
@ColumnWidth(15)
public class MergeTestBean {
    @ExcelProperty("种类")
    public String 种类;

    @ExcelProperty("城市公司A")
    public String 城市公司A;

    @ExcelProperty("供应商名称")
    public String 供应商名称;

    @ExcelProperty("城市公司B")
    public String 城市公司B;

    @ExcelProperty("合同")
    public String 合同;

    @ExcelProperty("公司属性")
    public String 公司属性;
}
