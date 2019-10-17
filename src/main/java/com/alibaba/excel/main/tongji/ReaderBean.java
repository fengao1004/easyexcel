package com.alibaba.excel.main.tongji;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReaderBean {
    @ExcelProperty(index = 1, value = "供应商名称")
    String gysName;

    @ExcelProperty(index = 4, value = "城市公司")
    String city;

    @ExcelProperty(index = 8, value = "合同名称")
    String htName;

    public static List<ReaderBean> fillList(List<ReaderBean> list) {
        String gysName = "";
        String city = "";
        String htName = "";
        for (ReaderBean bean : list) {
            if (bean.city == null || bean.city.equals("")) {
                bean.city = city;
            } else {
                city = bean.city;
            }
            if (bean.gysName == null || bean.gysName.equals("")) {
                bean.gysName = gysName;
            } else {
                gysName = bean.gysName;
            }
        }
        return list;
    }

}
