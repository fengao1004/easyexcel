package com.alibaba.excel.main.a20190916.step1;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Bean {
    @ExcelProperty("序号")
    public String 序号;
    @ExcelProperty("供应商名称")
    public String 供应商名称;
    @ExcelProperty("供应商级别")
    public String 供应商级别;
    @ExcelProperty("供应商资质")
    public String 供应商资质;
    @ExcelProperty("城市公司")
    public String 城市公司;
    @ExcelProperty("联系人-职位-联系方式")
    public String 联系人;
    @ExcelProperty("负责人-职位-联系方式")
    public String 负责人;
    @ExcelProperty("合作项目名称")
    public String 合作项目名称;
    @ExcelProperty("合同名称")
    public String 合同名称;
    @ExcelProperty("单位来源")
    public String 单位来源;
    @ExcelProperty("引用自（单位来源为引用其他区域、城市时填）  ")
    public String 引用自;
    @ExcelProperty("采购方式")
    public String 采购方式;
    @ExcelProperty("定标时间")
    public String 定标时间;
    @ExcelProperty("签约时间")
    public String 签约时间;
    @ExcelProperty("计划/实际履约完成时间")
    public String 计划完成时间;
    @ExcelProperty("目前状态")
    public String 目前状态;
    @ExcelProperty("单位优势")
    public String 单位优势;
    @ExcelProperty("单位短板")
    public String 单位短板;
    @ExcelProperty("目前合作主要问题及影响结果（本栏不填视为无问题）")
    public String 目前合作主要问题及影响结果;
    @ExcelProperty("未来预计将出现的问题及导致结果（本栏不填视为无问题） ")
    public String 未来预计将出现的问题及导致结果;
    @ExcelProperty("城市公司应对解决方案")
    public String 城市公司应对解决方案;
    @ExcelProperty("需要区域的支持")
    public String 需要区域的支持;
    @ExcelProperty("城市对于后续合作的建议")
    public String 城市对于后续合作的建议;

    public static void fill(List<Bean> list) {
        String 序号 = "";
        String 供应商名称 = "";
        String 供应商级别 = "";
        String 供应商资质 = "";
        String 城市公司 = "";

        for (Bean bean : list) {
            if (bean.序号 == null || bean.序号.equals("")) {
                bean.序号 = 序号;
            } else {
                序号 = bean.序号;
            }
            if (bean.供应商名称 == null || bean.供应商名称.equals("")) {
                bean.供应商名称 = 供应商名称;
            } else {
                供应商名称 = bean.供应商名称;
            }
            if (bean.供应商级别 == null || bean.供应商级别.equals("")) {
                bean.供应商级别 = 供应商级别;
            } else {
                供应商级别 = bean.供应商级别;
            }
            if (bean.供应商资质 == null || bean.供应商资质.equals("")) {
                bean.供应商资质 = 供应商资质;
            } else {
                供应商资质 = bean.供应商资质;
            }
            if (bean.城市公司 == null || bean.城市公司.equals("")) {
                bean.城市公司 = 城市公司;
            } else {
                城市公司 = bean.城市公司;
            }
        }

    }
}
