package com.alibaba.excel.main.a20191028;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Bean1 {
    @ExcelProperty(index=0)
    public   String 序号;
    @ExcelProperty(index=1)
    public   String 合同系统编号;
    @ExcelProperty(index=2)
    public   String 合同类型;
    @ExcelProperty(index=3)
    public   String 合同二级分类;
    @ExcelProperty(index=4)
    public   String 合同归档编号;
    @ExcelProperty(index=5)
    public   String 分期名称;
    @ExcelProperty(index=6)
    public   String 合同名称;
    @ExcelProperty(index=7)
    public   String 项目名称;
    @ExcelProperty(index=8)
    public   String 城市公司;
    @ExcelProperty(index=9)
    public   String 甲方名称;
    @ExcelProperty(index=10)
    public   String 乙方名称;
    @ExcelProperty(index=11)
    public   String 归档日期;
    @ExcelProperty(index=12)
    public   String 结算状态;
    @ExcelProperty(index=13)
    public   String 创建日期;
    @ExcelProperty(index=14)
    public   String 签章状态;
    @ExcelProperty(index=15)
    public   Long 合同金额;
    @ExcelProperty(index=16)
    public   Long 合同最新金额;
    @ExcelProperty(index=17)
    public   String 经办人名称;
    @ExcelProperty(index=18)
    public   String 采购模式;
    @ExcelProperty(index=19)
    public   String 采购方式;
    @ExcelProperty(index=20)
    public   String 合同状态;
    @ExcelProperty(index=21)
    public   String 收到合同时间;
    @ExcelProperty(index=22)
    public   String 是否收到合同;
    @ExcelProperty(index=23)
    public   String 审批通过日期;
    @ExcelProperty(index=24)
    public   String 拆分重归属次数;
    @ExcelProperty(index=25)
    public   String 模板名称;
    @ExcelProperty(index=26)
    public   String 区域;
    @ExcelProperty(index=27)
    public   String 签章日期;
    @ExcelProperty(index=28)
    public   String 是否商票支付;
}
