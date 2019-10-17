package com.alibaba.excel.main.a20190916.step1;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.MergeBean;
import com.alibaba.excel.main.util.MergeUtil;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private ExcelWriter excelWriter;
    private static ExcelWriter excelWriter1;
    Map<String, List<OutBean>> listMap = new HashMap<>();

    public static void main(String[] args) {

        Main main = new Main();
        main.init("总包", 0);
        main.init("精装", 1);
        main.init("景观", 2);
        main.init("幕墙", 3);
        main.init("门窗", 4);
        main.println();
    }

    private void println() {
        Map<String, Integer> map = new HashMap<>();
        map.put("总包", listMap.get("总包").size());
        map.put("精装", listMap.get("精装").size());
        map.put("景观", listMap.get("景观").size());
        map.put("幕墙", listMap.get("幕墙").size());
        map.put("门窗", listMap.get("门窗").size());
        MergeUtil mergeUtil = new MergeUtil();
        MergeBean bean1 = new MergeBean();
        bean1.lineIndex = 3;
        bean1.followIndex.add(0);
        MergeBean bean2 = new MergeBean();
        bean2.lineIndex = 1;
        MergeBean bean3 = new MergeBean();
        bean3.lineIndex = 2;
        MergeBean bean4 = new MergeBean();
        bean4.lineIndex = 4;
        bean4.importantLineInex = 3;
        MergeBean bean5 = new MergeBean();
        bean5.lineIndex = 5;
        bean5.importantLineInex = 3;
        MergeBean bean6 = new MergeBean();
        bean6.lineIndex = 6;
        bean6.importantLineInex = 3;
        mergeUtil.mergeBeans.add(bean1);
        mergeUtil.mergeBeans.add(bean2);
        mergeUtil.mergeBeans.add(bean3);
        mergeUtil.mergeBeans.add(bean4);
        mergeUtil.mergeBeans.add(bean5);
        mergeUtil.mergeBeans.add(bean6);
        excelWriter = EasyExcel.write(outPath, OutBean.class).registerWriteHandler(mergeUtil).build();
        mergeUtil.ready(listMap.get("总包"));
        write("总包", 0);
        mergeUtil.ready(listMap.get("精装"));

        write("精装", 1);
        mergeUtil.ready(listMap.get("景观"));

        write("景观", 2);
        mergeUtil.ready(listMap.get("幕墙"));

        write("幕墙", 3);
        mergeUtil.ready(listMap.get("门窗"));

        write("门窗", 4);
        excelWriter.finish();
    }

    String path = "D:\\smm\\excel\\20190916\\已签约.xls";
    String outPath = "D:\\smm\\excel\\20190916\\out1.xls";


    private void init(String typeName, int sheetIndex) {
        Reader<Bean> readerBeanReader = new Reader<>(Bean.class);
        List<Bean> beans = readerBeanReader.readExcel(path, sheetIndex);
        List<OutBean> outBeans = new ArrayList<>();
        Bean.fill(beans);
        Map<String, Integer> map = new HashMap<>();
        for (Bean bean : beans) {
            Integer integer = map.get(bean.供应商名称);
            if (integer == null || integer < 1) {
                integer = 0;
            }
            integer++;
            map.put(bean.供应商名称, integer);
        }
        int 城市合作 = 0;
        int 单城市 = 0;
        String 供应商名称 = "";
        for (Bean bean : beans) {
            OutBean outBean = OutBean.copyFromBean(bean);
            Integer integer = map.get(bean.供应商名称);
            if (integer > 1) {
                outBean.供应商模式 = "城市合作";
                if (!供应商名称.equals(bean.供应商名称)) {
                    城市合作++;
                }
            } else {
                outBean.供应商模式 = "单城市";
                if (!供应商名称.equals(bean.供应商名称)) {
                    单城市++;
                }
            }
            outBeans.add(outBean);
        }
        for (OutBean bean : outBeans) {
            Integer integer = map.get(bean.供应商名称);
            if (integer > 1) {
                bean.模式数量 = 城市合作;
            } else {
                bean.模式数量 = 单城市;
            }
        }
        List<OutBean> outBeansA = new ArrayList<>();
        List<OutBean> outBeansB = new ArrayList<>();
        for (OutBean bean : outBeans) {
            if (bean.供应商模式.equals("单城市")) {
                outBeansA.add(bean);
            }
            if (bean.供应商模式.equals("城市合作")) {
                outBeansB.add(bean);
            }
        }
        outBeans.clear();
        outBeans.addAll(outBeansA);
        outBeans.addAll(outBeansB);
        listMap.put(typeName, outBeans);
    }

    void write(String name, int sheetIndex) {
        List<OutBean> list = listMap.get(name);
        // 这里 需要指定写用哪个class去读
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetIndex, name).build();
        // 第一次写入会创建头
        excelWriter.write(list, writeSheet);
        // 第二次写入会在上一次写入的最后一行后面写入
    }
}
