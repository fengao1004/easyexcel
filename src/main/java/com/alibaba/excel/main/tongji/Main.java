package com.alibaba.excel.main.tongji;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.*;

public class Main {
    String outPath = "C:\\excelauto\\201909132321\\out.xls";
    Map<String, List<ReaderBeanByGYS>> map;
    Map<String, List<ReaderBeanC>> map2;
    Map<String, List<ReaderBeanC>> map3;
    List<OUTBeanA> list1;
    List<OUTBeanA> list2;
    List<OUTBeanA> list3;
    List<OUTBeanA> list4;
    List<OUTBeanA> list5;
    List<OUTBeanA> list6;
    private String[] stringCity;
    private String[] strinType;
    private ExcelWriter excelWriter;

    public static void main(String[] args) {
        new Main().handle();
    }

    public void handle() {
        map = new HashMap<String, List<ReaderBeanByGYS>>();
        map2 = new HashMap<String, List<ReaderBeanC>>();
        map3 = new HashMap<String, List<ReaderBeanC>>();
        list1 = new ArrayList<OUTBeanA>();
        list2 = new ArrayList<OUTBeanA>();
        list3 = new ArrayList<OUTBeanA>();
        list4 = new ArrayList<OUTBeanA>();
        list5 = new ArrayList<OUTBeanA>();
        list6 = new ArrayList<OUTBeanA>();
        Reader<ReaderBean> readerBeanReader = new Reader<>(ReaderBean.class);
        map.put("总包", ReaderBeanByGYS.transitionList(ReaderBean.fillList(readerBeanReader.readExcel("C:\\excelauto\\201909132321\\A.xls", 0))));
        map.put("精装", ReaderBeanByGYS.transitionList(ReaderBean.fillList(readerBeanReader.readExcel("C:\\excelauto\\201909132321\\A.xls", 1))));
        map.put("景观", ReaderBeanByGYS.transitionList(ReaderBean.fillList(readerBeanReader.readExcel("C:\\excelauto\\201909132321\\A.xls", 2))));
        map.put("幕墙", ReaderBeanByGYS.transitionList(ReaderBean.fillList(readerBeanReader.readExcel("C:\\excelauto\\201909132321\\A.xls", 3))));
        map.put("门窗", ReaderBeanByGYS.transitionList(ReaderBean.fillList(readerBeanReader.readExcel("C:\\excelauto\\201909132321\\A.xls", 4))));
        Reader<ReaderBeanC> readerBeanCReader = new Reader<ReaderBeanC>(ReaderBeanC.class);
        List<ReaderBeanC> readerBeanCS = readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\C.xls", 0);
        ReaderBeanC.fillList(readerBeanCS);
        map2.put("总包", ReaderBeanC.checkRepetition(readerBeanCS, map.get("总包")));

        List<ReaderBeanC> readerBeanJZ = readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\C.xls", 1);
        ReaderBeanC.fillList(readerBeanJZ);
        map2.put("精装", ReaderBeanC.checkRepetition(readerBeanJZ, map.get("精装")));

        List<ReaderBeanC> readerBeanJG = readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\C.xls", 2);
        ReaderBeanC.fillList(readerBeanJG);
        map2.put("景观", ReaderBeanC.checkRepetition(readerBeanJG, map.get("景观")));

        List<ReaderBeanC> readerBeanMQ = readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\C.xls", 3);
        ReaderBeanC.fillList(readerBeanMQ);
        map2.put("幕墙", ReaderBeanC.checkRepetition(readerBeanMQ, map.get("幕墙")));

        List<ReaderBeanC> readerBeanMC = readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\C.xls", 4);
        ReaderBeanC.fillList(readerBeanMC);
        map2.put("门窗", ReaderBeanC.checkRepetition(readerBeanMC, map.get("门窗")));

        map3.put("总包", ReaderBeanC.fillList(readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\B.xls", 0)));
        map3.put("精装", ReaderBeanC.fillList(readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\B.xls", 1)));
        map3.put("景观", ReaderBeanC.fillList(readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\B.xls", 2)));
        map3.put("门窗", ReaderBeanC.fillList(readerBeanCReader.readExcel("C:\\excelauto\\201909132321\\B.xls", 3)));
        initOut();
        out();
        excelWriter = EasyExcel.write(outPath, OUTBeanA.class).build();
        write("单城市", list1, 0);
        write("跨城市", list2, 1);
        write("1", list3, 2);
        write("2", list4, 3);
        write("单城市_1", list5, 4);
        write("单城市_2", list6, 5);
        excelWriter.finish();
    }

    private List<ReaderBeanC> quchong(List<ReaderBeanC> list3, String s) {
        List<ReaderBeanC> list = new ArrayList<>();
        for (ReaderBeanC outBeanA : list3) {
            boolean flag = true;
            for (ReaderBeanC outBeanB : list3) {
                if (outBeanA.gysName.equals(outBeanB.gysName) && outBeanA != outBeanB) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(s+"_供应商: "+outBeanA.gysName+" _城市公司: "+outBeanA.city);
                list.add(outBeanA);
            }
        }
        return list;
    }

    void write(String name, List<OUTBeanA> list, int sheetIndex) {
        // 这里 需要指定写用哪个class去读
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetIndex, name).build();
        // 第一次写入会创建头
        excelWriter.write(list, writeSheet);
        // 第二次写入会在上一次写入的最后一行后面写入
    }

    private void out() {
        Set<String> strings = map2.keySet();
        for (String s : strings) {
            List<ReaderBeanC> readerBeanCS = map2.get(s);
            out(readerBeanCS, s, list3);
            out(quchong(readerBeanCS,s), s, list5);
        }
        Set<String> strings1 = map3.keySet();
        for (String s : strings1) {
            List<ReaderBeanC> readerBeanCS = map3.get(s);
            out(readerBeanCS, s, list4);
            out(quchong(readerBeanCS, s), s, list6);
        }
    }

    private void initOut() {
        stringCity = new String[]{"青岛公司",
            "济南公司",
            "石家庄公司",
            "太原公司",
            "环京公司",
            "北京公司",
            "烟威龙公司",
            "兰州公司"};
        for (int i = 0; i < stringCity.length; i++) {
            list1.add(new OUTBeanA(stringCity[i]));
            list2.add(new OUTBeanA(stringCity[i]));
            list3.add(new OUTBeanA(stringCity[i]));
            list4.add(new OUTBeanA(stringCity[i]));
            list5.add(new OUTBeanA(stringCity[i]));
            list6.add(new OUTBeanA(stringCity[i]));
        }
        strinType = new String[]{"总包", "精装", "景观", "幕墙", "门窗"};
        Set<String> strings = map.keySet();
        for (String type : strings) {
            List<ReaderBeanByGYS> readerBeanByGYS = map.get(type);
            for (ReaderBeanByGYS beanByGYS : readerBeanByGYS) {
                if (beanByGYS.citys.size() == 1) {
                    for (int j = 0; j < stringCity.length; j++) {
                        if (stringCity[j].equals(beanByGYS.citys.get(0))) {
                            switch (type) {
                                case "总包":
                                    list1.get(j).zb++;
                                    list2.get(j).zb++;
                                    break;
                                case "精装":
                                    list1.get(j).jz++;
                                    list2.get(j).jz++;
                                    break;
                                case "景观":
                                    list1.get(j).jg++;
                                    list2.get(j).jg++;
                                    break;
                                case "幕墙":
                                    list1.get(j).mq++;
                                    list2.get(j).mq++;
                                    break;
                                case "门窗":
                                    list1.get(j).mc++;
                                    list2.get(j).mc++;
                                    break;
                            }
                        }
                    }
                } else {
                    for (int j = 0; j < stringCity.length; j++) {
                        for (String city : beanByGYS.citys) {
                            if (stringCity[j].equals(city)) {
                                sad(type, j, list2);
                            }
                        }
                    }
                }
            }

        }
    }

    private void sad(String type, int j, List<OUTBeanA> list2) {
        switch (type) {
            case "总包":
                list2.get(j).zb++;
                break;
            case "精装":
                list2.get(j).jz++;
                break;
            case "景观":
                list2.get(j).jg++;
                break;
            case "幕墙":
                list2.get(j).mq++;
                break;
            case "门窗":
                list2.get(j).mc++;
                break;
        }
    }

    private void out(List<ReaderBeanC> list, String type, List<OUTBeanA> list1) {
        for (int j = 0; j < stringCity.length; j++) {
            for (ReaderBeanC beanC : list) {
                if (beanC.city.equals(stringCity[j])) {
                    sad(type, j, list1);
                }
            }
        }
    }
}
