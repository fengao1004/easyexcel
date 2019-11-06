package com.alibaba.excel.main.a20191104;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    String needPath1 = "D:\\smm\\excel\\20191104\\number.xls";
    String needPath2 = "D:\\smm\\excel\\20191104\\all.xls";
    String outPath = "D:\\smm\\excel\\20191104\\out.xls";
    private List<Bean2> systemList;
    private Set<Bean2> list;
    private ExcelWriter excelWriter;

    public static void main(String[] args) {
        Main main = new Main();
        main.getSystemExcel();
        main.out();

    }

    private void out() {
        excelWriter = EasyExcel.write(outPath, Bean2.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "手机号码").build();
//        List<OutBean> list1 = new ArrayList<>();
//        int num = 1;
//        for (OutBean outBean : list) {
//            outBean.序号 = num;
//            list1.add(outBean);
//            num++;
//        }
        excelWriter.write(new ArrayList(list), writeSheet);
        excelWriter.finish();
    }

    public void getSystemExcel() {
        Reader<Bean> beanReader1 = new Reader<>(Bean.class);
        Reader<Bean2> beanReader2 = new Reader<>(Bean2.class);
        systemList = beanReader2.readExcel(needPath2, 0);
//        systemList.addAll(beanReader2.readExcel(needPath2, 1));
        List<Bean> beans = beanReader1.readExcel(needPath1, 0);
        list = new LinkedHashSet<>();
        Iterator<Bean2> iterator = systemList.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Bean2 next = iterator.next();
            if (!isNumberPhone(next.手机号码 + "")
                    || next.终端品牌.equals("维沃")
                    || next.终端品牌.equals("欧珀")
                    || next.终端品牌.equals("小米")
                    || next.终端品牌.equals("华为")
                    || next.终端品牌.equals("苹果")) {
                iterator.remove();
                continue;
            }
            System.out.println(index++);
        }
        List<Bean2> bean2s = beanReader2.readExcel(needPath2, 1);
        Iterator<Bean2> iterator2 = bean2s.iterator();
        while (iterator2.hasNext()) {
            Bean2 next = iterator2.next();
            if (!isNumberPhone(next.手机号码 + "")
                    || next.终端品牌.equals("苹果")) {
                iterator2.remove();
                continue;
            }
            System.out.println(index++);
        }
        systemList.addAll(bean2s);
        list.addAll(systemList);
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
