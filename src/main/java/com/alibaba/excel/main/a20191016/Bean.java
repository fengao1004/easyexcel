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
    @ColumnWidth(6)
    @ExcelProperty(value="序号",index = 0)
    public   String 序号;
    @ExcelProperty(value="供应商名称",index = 1)
    public   String 供应商名称;
    @ExcelProperty(value="供应商级别",index = 2)
    public   String 供应商级别;
    @ExcelProperty(value="供应商资质",index = 3)
    public   String 供应商资质;
    @ColumnWidth(15)
    @ExcelProperty(value="城市公司",index = 4)
    public   String 城市公司;
    @ExcelProperty(value="联系人-职位-联系方式",index =5)
    public   String 联系人;
    @ExcelProperty(value="负责人-职位-联系方式",index = 6)
    public   String 负责人;
    @ExcelProperty(value = "合作项目名称",index = 7)
    public   String 合作项目名称;
    @ExcelProperty(value="合同名称",index = 8)
    public   String 合同名称;
    @ExcelProperty(value="签约面积（单位：万平）",index = 9)
    public   String 签约面积;
    @ExcelProperty(value="签约金额（单位：万元）",index = 10)
    public   String 签约金额;
    @ExcelProperty(value="单方造价（单位：元/平米）",index = 11)
    public   String 单方造价;
    @ExcelProperty(value="单位来源",index = 12)
    public   String 单位来源;
    @ExcelProperty(value="引用自（单位来源为引用其他区域、城市时填）  ",index = 13)
    public   String 引用自;
    @ExcelProperty(value="采购方式",index = 14)
    public   String 采购方式;
    @ExcelProperty(value="定标时间",index = 15)
    public   String 定标时间;
    @ExcelProperty(value="签约时间",index = 16)
    public   String 签约时间;
    @ExcelProperty(value="计划/实际履约完成时间",index = 17)
    public   String 计划;
    @ExcelProperty(value="目前状态",index = 18)
    public   String 目前状态;
    @ExcelProperty(value="单位优势",index = 19)
    public   String 单位优势;
    @ExcelProperty(value="单位短板",index = 20)
    public   String 单位短板;
    @ExcelProperty(value="目前合作主要问题及影响结果（本栏不填视为无问题）",index = 21)
    public   String 目前合作;
    @ExcelProperty(value="未来预计将出现的问题及导致结果（本栏不填视为无问题） ",index = 22)
    public   String 未来预计 ;
    @ExcelProperty(value="城市公司应对解决方案",index = 23)
    public   String 城市公司应对解决方案;
    @ExcelProperty(value="需要区域的支持",index = 24)
    public   String 需要区域的支持;
    @ExcelProperty(value="城市对于后续合作的建议",index = 25)
    public   String 城市对于后续合作的建议;
    @ExcelProperty(value="是否特殊报备",index =26)
    public   String 是否特殊报备;
    @ExcelProperty(value="是否已经进场但还未签订合同",index = 27)
    public   String 是否已经进场但还未签订合同;
    @ExcelProperty(value="战略落地率",index = 28)
    public   String 战略落地率;
    @ExcelProperty(value="备注",index = 29)
    public   String 备注;
}
