package com.alibaba.excel.main.statisticalA;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.similarity.DemoData;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;

/**
 * 统计五大类的 城市 —— 项目数量 —— 地区
 */
public class StatisticalA {
    static String path = "D:\\资料\\数据源.xls";
    static String path1 = "D:\\资料\\结果.xls";
    private static List<DateBean> list;
    static Map<String, RowBean> rowBeanMap = new HashMap<>();
    static Set<RowBean> rowBeanSet = new TreeSet<>();

    public static void main(String[] args) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelWriter excelWriter = EasyExcel.write(path1, RowBean.class).build();
        DateBeanListener dateBeanListener = new DateBeanListener();
        DateBeanListener dateBeanListener1 = new DateBeanListener();
        DateBeanListener dateBeanListener2 = new DateBeanListener();
        DateBeanListener dateBeanListener3 = new DateBeanListener();
        DateBeanListener dateBeanListener4 = new DateBeanListener();

        EasyExcel.read(path, DateBean.class, dateBeanListener).sheet(0).doReadSync();
        list = dateBeanListener.list;
        handle("总包");
        List data1 = data();
        WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "总包").build();
        excelWriter.write(data1, writeSheet1);

        EasyExcel.read(path, DateBean.class, dateBeanListener1).sheet(1).doReadSync();
        list = dateBeanListener1.list;
        handle("精装");
        List data2 = data();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "精装").build();
        excelWriter.write(data2, writeSheet2);

        EasyExcel.read(path, DateBean.class, dateBeanListener2).sheet(2).doReadSync();
        list = dateBeanListener2.list;
        handle("景观");
        List data3 = data();
        WriteSheet writeSheet3 = EasyExcel.writerSheet(2, "景观").build();
        excelWriter.write(data3, writeSheet3);


        EasyExcel.read(path, DateBean.class, dateBeanListener3).sheet(3).doReadSync();
        list = dateBeanListener3.list;
        handle("幕墙");
        List data4 = data();
        WriteSheet writeSheet4 = EasyExcel.writerSheet(3, "幕墙").build();
        excelWriter.write(data4, writeSheet4);


        EasyExcel.read(path, DateBean.class, dateBeanListener4).sheet(4).doReadSync();
        list = dateBeanListener4.list;
        handle("门窗");
        List data5 = data();
        WriteSheet writeSheet5 = EasyExcel.writerSheet(4, "门窗").build();
        excelWriter.write(data5, writeSheet5);
        excelWriter.finish();

    }

    private static List data() {
        List<RowBean> list = new ArrayList<>();
        Iterator<RowBean> iterator = rowBeanSet.iterator();
        Map<String, Set<String>> map = new HashMap<>();
        while (iterator.hasNext()) {
            RowBean next = iterator.next();
            list.add(next);
        }
        RowBean rowBean1 = new RowBean();
        RowBean rowBean2 = new RowBean();
        for (RowBean rowBean:list) {
            if(rowBean.cityNum!=1){
                rowBean1.sjz +=rowBean.sjz;
                rowBean1.qd +=rowBean.qd;
                rowBean1.yanweilong +=rowBean.yanweilong;
                rowBean1.huanjing +=rowBean.huanjing;
                rowBean1.beijing +=rowBean.beijing;
                rowBean1.lanzhou +=rowBean.lanzhou;
                rowBean1.jn +=rowBean.jn;
                rowBean1.taiyuan +=rowBean.taiyuan;
                rowBean1.total +=rowBean.total;
            }
            rowBean2.sjz +=rowBean.sjz;
            rowBean2.qd +=rowBean.qd;
            rowBean2.yanweilong +=rowBean.yanweilong;
            rowBean2.huanjing +=rowBean.huanjing;
            rowBean2.beijing +=rowBean.beijing;
            rowBean2.lanzhou +=rowBean.lanzhou;
            rowBean2.jn +=rowBean.jn;
            rowBean2.taiyuan +=rowBean.taiyuan;
            rowBean2.total +=rowBean.total;
        }
        rowBean1.teamworkModel ="跨城市合作合计";
        rowBean2.teamworkModel ="城市合作合计";
        list.add(rowBean1);
        list.add(rowBean2);
        rowBeanSet.clear();
        return list;
    }

    private static void handle(String type) {
        System.out.println("====================================== start" + System.currentTimeMillis());
        List<CompanyBean> companyBeans = new ArrayList<>();
        Map<String, CompanyBean> map1 = new HashMap<>();
        Map<Integer, List<CompanyBean>> map2 = new HashMap<>();
        for (DateBean bean : list) {
            CompanyBean companyBean = map1.get(bean.companyNmae);
            if (companyBean == null) {
                companyBean = new CompanyBean();
                companyBean.cityList = new HashSet<>();
                companyBean.map = new HashMap<>();
                companyBean.name = bean.companyNmae;
                map1.put(bean.companyNmae, companyBean);
                companyBeans.add(companyBean);
            }
            companyBean.cityList.add(bean.cityName);
            List<String> strings = companyBean.map.get(bean.cityName);
            if (strings == null) {
                strings = new ArrayList<>();
                companyBean.map.put(bean.cityName, strings);
            }
            strings.add(bean.projectNmae);
        }
        int num = 0;
        for (CompanyBean companyBean : companyBeans) {
            List<CompanyBean> companyBeanList = map2.get(companyBean.cityList.size());
            if (companyBeanList == null) {
                companyBeanList = new ArrayList<>();
                map2.put(companyBean.cityList.size(), companyBeanList);
            }
            companyBeanList.add(companyBean);
            for (String str : companyBean.cityList) {
                num += companyBean.map.get(str).size();
            }
        }
        Set<Integer> integers = map2.keySet();
        Object[] objects = integers.toArray();
        Arrays.sort(objects);
        for (Object integer : objects) {
            List<CompanyBean> companyBeans1 = map2.get(integer);
            System.out.println(type + "类:" + integer + "个合作城市的公司有" + companyBeans1.size() + "个");
            createA(type, integer, companyBeans1);
        }
        System.out.println("====================================== end" + System.currentTimeMillis());
        System.out.println("");
    }


    private static void createA(String type, Object number, List<CompanyBean> companyBeans1) {
        String hetongNum = "跨" + number + "个城市";
        RowBean rowBean = new RowBean();
        rowBean.cityNum = (Integer) number;
        rowBean.teamworkModel = hetongNum;
        for (CompanyBean companyBean : companyBeans1) {
            Set<String> cityList = companyBean.cityList;
            Map<String, List<String>> map = companyBean.map;
            for (String city : cityList) {
                rowBean.total++;
                switch (city) {
                    case "青岛公司":
                        rowBean.qd++;
                        break;
                    case "石家庄公司":
                        rowBean.sjz++;
                        break;
                    case "烟威龙公司":
                        rowBean.yanweilong++;

                        break;
                    case "环京公司":
                        rowBean.huanjing++;

                        break;
                    case "北京公司":
                        rowBean.beijing++;
                        break;
                    case "济南公司":
                        rowBean.jn++;
                        break;
                    case "兰州公司":
                        rowBean.lanzhou++;
                        break;
                    case "太原公司":
                        rowBean.taiyuan++;
                        break;
                }
            }
        }
        rowBeanSet.add(rowBean);
    }
}
