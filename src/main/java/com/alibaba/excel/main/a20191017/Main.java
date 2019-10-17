package com.alibaba.excel.main.a20191017;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;

public class Main {
    static String path1 = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\总包、精装类模板.xls";
    static String path2 = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\甲分包类模板.xls";
    static String path3 = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\材料设备等其他类模板.xls";
    static String path4 = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\服务咨询类模板.xls";

    static String outPath = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\out\\out.xls";
    static String outPath2 = "D:\\smm\\excel\\20191016\\北京区域评价表20191016新\\out\\out2.xls";
    private static ExcelWriter excelWriter;
    private static Map<String, List<Bean>> map;
    static int del = 0;
    private static int num;
    static List<List<Bean>> lists = new ArrayList<>();
    static List<Bean2> bean2List = new ArrayList<>();

    public static void main(String[] args) {
        a(path1, 7);
        a(path2, 6);
        a(path3, 1);
        a(path4, 2);
        bean2List.add(new Bean2("总包-过程评估", lists.get(0)));
        bean2List.add(new Bean2("精装-过程评估", lists.get(1)));
        bean2List.add(new Bean2("总包-交付评估（毛坯交付）", lists.get(2)));
        bean2List.add(new Bean2("总包-交付评估（精装交付）", lists.get(3)));
        bean2List.add(new Bean2("总包-交付评估（毛坯精装同时交付） ", lists.get(4)));
        bean2List.add(new Bean2("精装-交付评估", lists.get(5)));
        bean2List.add(new Bean2("总包、精装-后评估", lists.get(6)));
        bean2List.add(new Bean2("外窗类-交付评估", lists.get(7)));
        bean2List.add(new Bean2("智能化-交付评估", lists.get(8)));
        bean2List.add(new Bean2("景观-交付评估", lists.get(9)));
        bean2List.add(new Bean2("其他分包-交付评估 ", lists.get(10)));
        bean2List.add(new Bean2("分包类-后评估", lists.get(11)));
        bean2List.add(new Bean2("景观-后评估", lists.get(12)));
        bean2List.add(new Bean2("材料设备-交付评价", lists.get(13)));
        bean2List.add(new Bean2("服务咨询类（设计、其他）-交付评估", lists.get(14)));
        bean2List.add(new Bean2("造价咨询类-交付评估（只针对成本）", lists.get(15)));
        List<OutBean> list = new ArrayList<>();
        for (Bean2 bean2 : bean2List) {
            String name = bean2.name;
            List<Bean> list1 = bean2.list;
            for (Bean bean : list1) {
                OutBean outBean = new OutBean(bean);
                if (bean.评估维度 == null) {
                    int a = 1;
                }
                outBean.子模板描述 = name;
                list.add(outBean);
            }
        }
        String last = "";
        String lastId = "";
        int num = 1;
        for (OutBean outBean : list) {
            if (last.equals(outBean.子模板描述)) {
                outBean.子模板编号 = lastId;
            } else {
                outBean.子模板编号 = "PGBM" + (num > 9 ? num : "0" + num);
                lastId = outBean.子模板编号;
                last = outBean.子模板描述;
                num++;
            }
        }
        num = 1;
        for (OutBean outBean : list) {
            int i = list.indexOf(outBean);
            if (i > 0) {
                if (list.get(i - 1).评审维度描述 == null) {
                    int a = 0;
                }
                if (list.get(i - 1).评审维度描述.equals(list.get(i).评审维度描述)) {
                    list.get(i).评审维度 = list.get(i - 1).评审维度;
                } else {
                    if (list.get(i - 1).子模板编号.equals(list.get(i).子模板编号)) {
                        list.get(i).评审维度 = list.get(i).子模板编号 + (num > 9 ? num : "0" + num);
                        num++;
                    } else {
                        num = 1;
                        list.get(i).评审维度 = list.get(i).子模板编号 + (num > 9 ? num : "0" + num);
                        num++;
                    }
                }
            } else {
                list.get(i).评审维度 = list.get(i).子模板编号 + (num > 9 ? num : "0" + num);
                num++;
            }
        }
        num = 1;
        for (OutBean outBean : list) {
            int i = list.indexOf(outBean);
            if (i > 0) {
                if ((list.get(i - 1).评审指标描述 + list.get(i - 1).评审标准描述).equals(list.get(i).评审指标描述 + list.get(i).评审标准描述)) {
                    list.get(i).评审指标 = list.get(i - 1).评审指标;
                } else {
                    if (list.get(i - 1).评审维度.equals(list.get(i).评审维度)) {
                        list.get(i).评审指标 = list.get(i).评审维度 + (num > 9 ? num : "0" + num);
                        num++;
                    } else {
                        num = 1;
                        list.get(i).评审指标 = list.get(i).评审维度 + (num > 9 ? num : "0" + num);
                        num++;
                    }
                }
            } else {
                list.get(i).评审指标 = list.get(i).评审维度 + (num > 9 ? num : "0" + num);
                num++;
            }

        }
        List<OutBean2> list1 = new ArrayList<>();
        for (OutBean outBean : list) {
            int i = list.indexOf(outBean);
            if (i > 0) {
                if (!list.get(i - 1).评审维度.equals(list.get(i).评审维度)) {
                    list1.add(new OutBean2(outBean.子模板编号,outBean.子模板描述,outBean.评审维度,outBean.评审维度描述));
                }
            } else {
                list1.add(new OutBean2(outBean.子模板编号,outBean.子模板描述,outBean.评审维度,outBean.评审维度描述));
            }
        }
        excelWriter = EasyExcel.write(outPath, OutBean.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "test").build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
        excelWriter = EasyExcel.write(outPath2, OutBean2.class).build();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(0, "test").build();
        excelWriter.write(list1, writeSheet2);
        excelWriter.finish();
    }

    private static void a(String path, int i) {
        for (int j = 0; j < i; j++) {
            Reader<Bean> beanReader = new Reader<>(Bean.class);
            List<Bean> beans = beanReader.readExcel(path, j);
            lists.add(beans);
        }

    }


}
