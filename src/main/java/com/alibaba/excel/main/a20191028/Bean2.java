package com.alibaba.excel.main.a20191028;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(25)
public class Bean2 {
    @ExcelProperty(index = 0, value = "序号")
    public String 序号;
    @ExcelProperty(index = 1, value = "供应商名称（合同主体）")
    public String 供应商名称;
    @ExcelProperty(index = 2, value = "供应商名称（施工主体）")
    public String 供应商名称施工主体;
    @ExcelProperty(index = 3, value = "供应商级别")
    public String 供应商级别;
    @ExcelProperty(index = 4, value = "供应商资质")
    public String 供应商资质;
    @ExcelProperty(index = 5, value = "城市公司")
    public String 城市公司;
    @ExcelProperty(index = 6, value = "联系人-职位-联系方式")
    public String 联系人联系方式;
    @ExcelProperty(index = 7, value = "负责人-职位-联系方式")
    public String 负责人联系方式;
    @ExcelProperty(index = 8, value = "合作项目名称（工程名）")
    public String 合作项目名称工程名;
    @ExcelProperty(index = 9, value = "合作项目名称（案名）")
    public String 合作项目名称案名;
    @ExcelProperty(index = 10, value = "合同名称")
    @ColumnWidth(40)
    public String 合同名称;
    @ColumnWidth(40)
    @ExcelProperty(index = 11, value = "签约金额（单位：万元）")
    public String 签约金额;
    @ColumnWidth(40)
    @ExcelProperty(index = 12, value = "最新合同金额")
    public String 最新合同金额;
    @ColumnWidth(40)
    @ExcelProperty(index = 13, value = "查询的合同名称")
    public String 查询的合同名称;
    @ExcelProperty(index = 14, value = "查询的项目名称")
    public String 查询的项目名称;
    @ExcelProperty(index = 15, value = "查询的城市公司")
    public String 查询的城市公司;
    @ExcelProperty(index = 16, value = "查询的甲方名称")
    public String 查询的甲方名称;
    @ExcelProperty(index = 17, value = "查询的乙方名称")
    public String 查询的乙方名称;
}
