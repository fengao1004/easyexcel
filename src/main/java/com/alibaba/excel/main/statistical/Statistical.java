package com.alibaba.excel.main.statistical;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.similarity.DemoData;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;

/**
 * 统计五大类的 城市 —— 项目数量 —— 地区
 */
public class Statistical {
    static String path = "D:\\资料\\数据源.xls";
    static String path1 = "D:\\资料\\结果.xls";
    private static List<DateBean> list;
    static Map<String, RowBean> rowBeanMap = new HashMap<>();
    static Set<RowBeanA> rowBeanSet = new TreeSet<>();

    public static void main(String[] args) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        ExcelWriter excelWriter = EasyExcel.write(path1, RowBeanA.class).build();
        DateBeanListener dateBeanListener = new DateBeanListener();
        DateBeanListener dateBeanListener1 = new DateBeanListener();
        DateBeanListener dateBeanListener2 = new DateBeanListener();
        DateBeanListener dateBeanListener3 = new DateBeanListener();
        DateBeanListener dateBeanListener4 = new DateBeanListener();

        EasyExcel.read(path, DateBean.class, dateBeanListener).sheet(0).doReadSync();
        list = dateBeanListener.list;
        handle("总包");
        List data1 = data();
        WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "总包").registerWriteHandler(new MyLoopMergeStrategy(data1)).build();
        excelWriter.write(data1, writeSheet1);

        EasyExcel.read(path, DateBean.class, dateBeanListener1).sheet(1).doReadSync();
        list = dateBeanListener1.list;
        handle("精装");
        List data2 = data();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "精装").registerWriteHandler(new MyLoopMergeStrategy(data2)).build();
        excelWriter.write(data2, writeSheet2);

        EasyExcel.read(path, DateBean.class, dateBeanListener2).sheet(2).doReadSync();
        list = dateBeanListener2.list;
        handle("景观");
        List data3 = data();
        WriteSheet writeSheet3 = EasyExcel.writerSheet(2, "景观").registerWriteHandler(new MyLoopMergeStrategy(data3)).build();
        excelWriter.write(data3, writeSheet3);


        EasyExcel.read(path, DateBean.class, dateBeanListener3).sheet(3).doReadSync();
        list = dateBeanListener3.list;
        handle("幕墙");
        List data4 = data();
        WriteSheet writeSheet4 = EasyExcel.writerSheet(3, "幕墙").registerWriteHandler(new MyLoopMergeStrategy(data4)).build();
        excelWriter.write(data4, writeSheet4);


        EasyExcel.read(path, DateBean.class, dateBeanListener4).sheet(4).doReadSync();
        list = dateBeanListener4.list;
        handle("门窗");
        List data5 = data();
        WriteSheet writeSheet5 = EasyExcel.writerSheet(4, "门窗").registerWriteHandler(new MyLoopMergeStrategy(data5)).build();
        excelWriter.write(data5, writeSheet5);
        excelWriter.finish();

    }

    private static List data() {
        List<RowBeanA> list = new ArrayList<>();
        Iterator<RowBeanA> iterator = rowBeanSet.iterator();
        Map<String, Set<String>> map = new HashMap<>();
        while (iterator.hasNext()) {
            RowBeanA next = iterator.next();
            Set<String> rowBeanAS = map.get(next.teamworkModel + next.contractNum);
            if (rowBeanAS == null) {
                rowBeanAS = new HashSet<>();
                map.put(next.teamworkModel + next.contractNum, rowBeanAS);
            }
            rowBeanAS.add(next.name);
            list.add(next);
        }
        for (RowBeanA beanA : list) {
            String s = beanA.teamworkModel + beanA.contractNum;
            int size = map.get(s).size();
            beanA.companyNum = size;
        }
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

    private static void create(String type, Object number, List<CompanyBean> companyBeans1) {
        String[] citys = new String[]{"青岛公司", "济南公司", "石家庄公司", "太原公司", "烟威龙公司", "北京公司", "环京公司", "兰州公司"};
        for (CompanyBean companyBean : companyBeans1) {
            RowBean rowBean = new RowBean();
            int total = 0;
            rowBean.cityNum = companyBean.cityList.size();
            for (String city : citys) {
                List<String> strings = companyBean.map.get(city);
                if (strings != null && strings.size() > 0) {
                    String str = "共" + strings.size() + "份合同：\r\n";
                    for (String htong : strings) {
                        str += htong + ",\r\n";
                    }
                    switch (city) {
                        case "青岛公司":
                            rowBean.qd = str;
                            break;
                        case "济南公司":
                            rowBean.jn = str;
                            break;
                        case "石家庄公司":
                            rowBean.sjz = str;
                            break;
                        case "太原公司":
                            rowBean.taiyuan = str;
                            break;
                        case "烟威龙公司":
                            rowBean.yanweilong = str;
                            break;
                        case "北京公司":
                            rowBean.beijing = str;
                            break;
                        case "环京公司":
                            rowBean.huanjing = str;
                            break;
                        case "兰州公司":
                            rowBean.lanzhou = str;
                            break;
                    }
                    total += strings.size();
                }
            }
            rowBean.name = companyBean.name;
            rowBean.total = total;
            rowBean.contractNumInt = total;
            rowBean.teamworkModel = number + "个合作城市";
            rowBean.contractNum = total + "个合同";
//            rowBeanSet.add(rowBean);
        }

    }

    private static void createA(String type, Object number, List<CompanyBean> companyBeans1) {
        String[] citys = new String[]{"青岛公司", "济南公司", "石家庄公司", "太原公司", "烟威龙公司", "北京公司", "环京公司", "兰州公司"};
        for (CompanyBean companyBean : companyBeans1) {
            RowBeanA rowBean = new RowBeanA();
            int total = 0;
            rowBean.cityNum = companyBean.cityList.size();
            for (String city : citys) {
                List<String> strings = companyBean.map.get(city);
                if (strings != null && strings.size() > 0) {
                    total += strings.size();
                }
            }
            rowBean.name = companyBean.name;
            rowBean.total = total;
            rowBean.contractNumInt = total;
            rowBean.teamworkModel = number + "个合作城市";
            rowBean.contractNum = total + "个合同";
//            rowBeanSet.add(rowBean);
            handle(rowBean, companyBean);
        }

    }

    private static void handle(RowBeanA rowBean, CompanyBean companyBean) {
        Map<String, List<String>> map = companyBean.map;
        String[] citys = new String[]{"青岛公司", "济南公司", "石家庄公司", "太原公司", "烟威龙公司", "北京公司", "环京公司", "兰州公司"};
        for (String city : citys) {
            List<String> strings = companyBean.map.get(city);
            if (strings != null && strings.size() > 0) {
                for (String str : strings) {
                    RowBeanA copy = rowBean.createCopy();
                    copy.htong = str;
                    copy.cityName = city;
                    copy.cityhrtongnum = strings.size();
                    rowBeanSet.add(copy);
                }
            }
        }
    }

    private static void println(String type, Object number, List<CompanyBean> companyBeans1) {
        Map<Integer, List<CompanyBean>> map = new HashMap<>();
        for (CompanyBean bean : companyBeans1) {
            int num = 0;
            Set<String> strings = bean.map.keySet();
            for (String city : strings) {
                List<String> strings1 = bean.map.get(city);
                num += strings1.size();
            }
            List<CompanyBean> companyBeans = map.get(num);
            if (companyBeans == null) {
                companyBeans = new ArrayList<>();
                map.put(num, companyBeans);
            }
            companyBeans.add(bean);
        }
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            System.out.println("有" + integer + "个合同的公司 共有" + map.get(integer).size() + "家,分别是：");
            String s = "共有" + map.get(integer).size() + "个公司，其中:\r\n";
            List<CompanyBean> companyBeans = map.get(integer);
//            Map<String, Integer> map1 = new HashMap<>();
//            for (CompanyBean bean : companyBeans) {
//                System.out.print(bean.name + ", ");
//                Set<String> strings = bean.map.keySet();
//                for (String city : strings) {
//                    List<String> strings1 = bean.map.get(city);
//                    Integer integer1 = map1.get(city);
//                    if (integer1 == null || integer1 < 1) {
//                        integer1 = 0;
//                    }
//                    integer1 = integer1 + strings1.size();
//                    map1.put(city, integer1);
//                }
//            }
            System.out.println("");
            for (CompanyBean bean : companyBeans) {
                Map<String, List<String>> map1 = bean.map;
                Set<String> strings = map1.keySet();
                s += bean.name + "公司:";
                for (String s1 : strings) {
                    List<String> strings1 = map1.get(s1);
                    s = s + s1 + "共" + strings1.size() + "个合同，";
                }
                s += "\r\n";
            }
            String key = number + "个合作城市_" + integer + "个合同";
            RowBean rowBean = rowBeanMap.get(key);
            if (rowBean == null) {
                rowBean = new RowBean();
                rowBean.teamworkModel = number + "个合作城市";
                rowBean.contractNum = integer + "个合同";
                rowBean.contractNumInt = integer;
                rowBean.cityNum = Integer.parseInt(number.toString());
//                rowBeanSet.add(rowBean);
                rowBeanMap.put(key, rowBean);
                rowBean.total = 0;
            }
            rowBean.total += map.get(integer).size();
//            switch (type) {
//                case "总包":
//                    rowBean.typeZB = s;
//                    break;
//                case "精装":
//                    rowBean.typeJZ = s;
//                    break;
//                case "景观":
//                    rowBean.typeJG = s;
//                    break;
//                case "幕墙":
//                    rowBean.typeMQ = s;
//                    break;
//                case "门窗":
//                    rowBean.typeMC = s;
//                    break;
//            }
            System.out.println(s);
            System.out.println();
        }
        System.out.println("```````````````````````````````````````````````````");
    }

}
