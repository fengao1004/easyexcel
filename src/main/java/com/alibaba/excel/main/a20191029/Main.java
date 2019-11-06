package com.alibaba.excel.main.a20191029;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    String needPath1 = "D:\\smm\\excel\\20191029\\old.xls";
    String needPath2 = "D:\\smm\\excel\\20191029\\手机号码.xls";
    String outPath = "D:\\smm\\excel\\20191029\\out.xls";
    private List<Bean> systemList;
    private Set<OutBean> list;
    private ExcelWriter excelWriter;

    public static void main(String[] args) {
        Main main = new Main();
        main.getSystemExcel();
        main.out();

    }

    private void out() {
        excelWriter = EasyExcel.write(outPath, Bean.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "手机号码").build();
//        List<OutBean> list1 = new ArrayList<>();
//        int num = 1;
//        for (OutBean outBean : list) {
//            outBean.序号 = num;
//            list1.add(outBean);
//            num++;
//        }
        excelWriter.write(systemList, writeSheet);
        excelWriter.finish();
    }

    public void getSystemExcel() {
        Reader<Bean> beanReader = new Reader<>(Bean.class);
        systemList = beanReader.readExcel(needPath2, 0);
        List<Bean> beans = beanReader.readExcel(needPath1, 0);
        list = new LinkedHashSet<>();
        Iterator<Bean> iterator = systemList.iterator();
        while (iterator.hasNext()) {
            Bean next = iterator.next();
            for (Bean bean : beans) {
                if (bean.手机号码.equals(next.手机号码)) {
                    iterator.remove();
                    break;
                }
            }
        }
        System.out.println(systemList.size());
    }

    private boolean isNumberPhone(String str) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(16([0-9]))|(17([0-9]))|(19([0-9]))|(18[0-9]))\\d{8}$";
        if (str.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }
}
