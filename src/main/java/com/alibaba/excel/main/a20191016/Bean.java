package com.alibaba.excel.main.a20191016;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@HeadRowHeight(20)
@ContentRowHeight(25)
@ColumnWidth(25)
public class Bean {
    @ExcelProperty("序号")
    @ColumnWidth(5)
    public String 序号 ;
    @ExcelProperty("供应商名称")
    public String 供应商名称 ;
    @ExcelProperty("供应商级别")
    public String 供应商级别;
    @ExcelProperty("供应商资质")
    public String 供应商资质 ;
    @ColumnWidth(15)
    @ExcelProperty("城市公司")
    public String 城市公司;
    @ExcelProperty("合作项目名称")
    public String 合作项目名称 ;
    @ExcelProperty("合同名称")
    public String 合同名称;
    @ExcelProperty("单位来源")
    public String 单位来源 ;
    @ExcelProperty("签约面积（单位：万平）")
    public String 签约面积 ;
    @ExcelProperty("签约金额（单位：万元）")
    public String 签约金额 ;
    @ExcelProperty("单方造价（单位：元/平米）")
    public String 单方造价 ;
    @ExcelProperty("引用自（单位来源为引用其他区域、城市时填）  ")
    public String 引用自 ;
    @ExcelProperty("采购方式")
    public String 采购方式 ;
    @ExcelProperty("定标时间")
    public String 定标时间;
    @ExcelProperty("签约时间")
    public String 签约时间;
    @ExcelProperty("计划/实际履约完成时间")
    public String 计划 ;
    @ExcelProperty("目前状态")
    public String 目前状态 ;
    @ExcelProperty("单位优势")
    public String 单位优势 ;
    @ExcelProperty("单位短板")
    public String 单位短板;
    @ExcelProperty("目前合作主要问题及影响结果（本栏不填视为无问题）")
    public String 目前合作主要问题及影响结果 ;
    @ExcelProperty("未来预计将出现的问题及导致结果（本栏不填视为无问题） ")
    public String 未来预计将出现的问题及导致结果 ;
    @ExcelProperty("城市公司应对解决方案")
    public String 城市公司应对解决方案 ;
    @ExcelProperty("需要区域的支持")
    public String 需要区域的支持;
    @ExcelProperty("城市对于后续合作的建议")
    public String 城市对于后续合作的建议 ;
    @ExcelProperty("备注")
    public String 备注 ;
    @ExcelProperty("2019年前三季度战略落地率战略落地率（%）")
    public String 落地率 ;
}
