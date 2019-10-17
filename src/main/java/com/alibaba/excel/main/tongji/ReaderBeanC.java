package com.alibaba.excel.main.tongji;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReaderBeanC {
    @ExcelProperty(index = 2, value = "供应商名称")
    String gysName;

    @ExcelProperty(index = 1, value = "城市公司")
    String city;

    public static List<ReaderBeanC> fillList(List<ReaderBeanC> list) {
        String city = "";
        for (ReaderBeanC bean : list) {
            if (bean.city == null || bean.city.equals("")) {
                bean.city = city;
            } else {
                city = bean.city;
            }
        }
        return list;
    }

    public static List<ReaderBeanC> checkRepetition(List<ReaderBeanC> list1, List<ReaderBeanByGYS> list2) {
        List<Integer> list = new ArrayList<Integer>();
        int index = -1;
        for (ReaderBeanC beanC : list1) {
            index++;
            for (ReaderBeanByGYS gys : list2) {
                if (gys.gysName.equals(beanC.gysName)) {
                    list.add(index);
                    break;
                }
            }
        }
        for (int i = list.size() - 1; i > -1; i--) {
            list1.remove(list.get(i));
        }
        return list1;
    }

}
