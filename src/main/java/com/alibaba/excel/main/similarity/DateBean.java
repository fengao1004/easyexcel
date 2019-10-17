package com.alibaba.excel.main.similarity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
public class DateBean {
    @ExcelProperty(value = {"合同名称"}, index = 9)
    public String contractnName;


    @ExcelProperty(value = {"工程类别"}, index = 12)
    public String projectType;

    @ExcelIgnore
    public Set<SetNode> matchSet = new TreeSet<>();
}
