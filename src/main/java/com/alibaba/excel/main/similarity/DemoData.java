package com.alibaba.excel.main.similarity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Data
public class DemoData {
//    @ExcelProperty(value ={"序号"},index = 0)
//    private int index;

    @ExcelProperty(value ={"序号"},index =0)
    public String num;

    @ExcelProperty(value ={"合同名称"},index =8)
    public String contractnName;
}
