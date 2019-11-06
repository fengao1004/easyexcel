package com.alibaba.excel.main.a20191016;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.MergeBean;
import com.alibaba.excel.main.util.MergeUtil;
import com.alibaba.excel.main.util.PullDownList;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;

public class Main {
    static String path = "D:\\smm\\excel\\20191016\\五大类汇总表\\五大类汇总表\\土建总包.xls";
    static String outPath = "D:\\smm\\excel\\20191016\\out\\";
    private static ExcelWriter excelWriter;
    private static Map<String, List<Bean>> map;
    static int del = 0;
    private static int num;

    public static void main(String[] args) {
        map = new HashMap<>();
        a(0, "土建总承包单位");
        a(1, "精装修单位");
        a(2, "景观单位");
        a(3, "门窗单位");
        a(4, "幕墙单位");
        Set<String> citySet = new HashSet<>();
        Set<String> strings = map.keySet();
        for (String s : strings) {
            String city = s.split("%&%")[0];
            citySet.add(city);
        }
        LinkedHashMap<String, LinkedHashMap<String, List<Bean>>> map1 = new LinkedHashMap<>();
        int total = 0;
        for (String city : citySet) {
            List<Bean> beans1 = map.get(city + "%&%" + "土建总承包单位");
            List<Bean> beans2 = map.get(city + "%&%" + "精装修单位");
            List<Bean> beans3 = map.get(city + "%&%" + "景观单位");
            List<Bean> beans4 = map.get(city + "%&%" + "门窗单位");
            List<Bean> beans5 = map.get(city + "%&%" + "幕墙单位");
            LinkedHashMap<String, List<Bean>> map2 = new LinkedHashMap<>();
            if (beans1 != null) {
                map2.put("土建总承包单位", beans1);
                total += beans1.size();
            }
            if (beans2 != null) {
                map2.put("精装修单位", beans2);
                total += beans2.size();
            }
            if (beans3 != null) {
                map2.put("景观单位", beans3);
                total += beans3.size();

            }
            if (beans4 != null) {
                map2.put("门窗单位", beans4);
                total += beans4.size();

            }
            if (beans5 != null) {
                map2.put("幕墙单位", beans5);
                total += beans5.size();

            }
            map1.put(city, map2);
        }
        for (String city : citySet) {
            LinkedHashMap<String, List<Bean>> map = map1.get(city);
            Set<String> strings1 = map.keySet();
            MergeUtil mergeUtil = new MergeUtil();
            MergeBean bean1 = new MergeBean();
            bean1.lineIndex = 1;
            bean1.followIndex.add(0);
            MergeBean bean2 = new MergeBean();
            bean2.lineIndex = 2;
            bean2.importantLineInex = 1;
            MergeBean bean3 = new MergeBean();
            bean3.lineIndex = 3;
            bean3.importantLineInex = 1;
            mergeUtil.mergeBeans.add(bean1);
            mergeUtil.mergeBeans.add(bean2);
            mergeUtil.mergeBeans.add(bean3);
            PullDownList pullDownList = new PullDownList();
            excelWriter = EasyExcel.write(outPath + city + ".xls", Bean.class).registerWriteHandler(mergeUtil).registerWriteHandler(pullDownList).build();
            int index = 0;
            for (String s : strings1) {
                List<Bean> list = map.get(s);
                mergeUtil.ready(list);
                WriteSheet writeSheet = EasyExcel.writerSheet(index, s).build();
                pullDownList.size = list.size();
                excelWriter.write(list, writeSheet);
                num += list.size();
                index++;
            }
            excelWriter.finish();
        }
        System.out.println(num);
    }

    private static void a(int i, String s) {
        Reader<Bean> beanReader = new Reader<>(Bean.class);
        List<Bean> beans = beanReader.readExcel(path, i);
        int num = 0;
        for (Bean bean : beans) {
            List<Bean> beans1 = map.get(bean.城市公司 + "%&%" + s);
//            if (bean.合同名称 != null) {
            if (beans1 == null) {
                beans1 = new ArrayList<>();
            }
            bean.定标时间 = bean.定标时间 == null ? "" : bean.定标时间.replace("00:00:00", "");
            bean.签约时间 = bean.签约时间 == null ? "" : bean.签约时间.replace("00:00:00", "");
            bean.计划 = bean.计划 == null ? "" : bean.计划.replace("00:00:00", "");
            beans1.add(bean);
//                if (bean.序号.split(".") == null || bean.序号.split(".").length == 0) {
            bean.序号 = bean.序号.split("\\.")[0];
//                }
            map.put(bean.城市公司 + "%&%" + s, beans1);
//            } else {
//                del++;
//            }
        }
    }

    void write(String name, int sheetIndex) {
//        List<Bean> list = listMap.get(name);
        // 这里 需要指定写用哪个class去读
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetIndex, name).build();
        // 第一次写入会创建头
//        excelWriter.write(list, writeSheet);
        // 第二次写入会在上一次写入的最后一行后面写入
    }

}
