package com.alibaba.excel.main.similarity;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.main.util.SimilarityRatio;

import java.util.*;

public class A {
    static String fileName1 = "D:\\资料\\quan.xls";
    static String fileName2 = "D:\\资料\\fan.xls";
    public static List<DemoData> listAll = new ArrayList<>();
    public static List<DateBean> listChoose = new ArrayList<>();

    public static void main(String[] args) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        DemoDataListener demoDataListener = new DemoDataListener();
        DemoDataBeanListener dataBeanListener = new DemoDataBeanListener();
        listAll = demoDataListener.listQuanTJ;
        listChoose = dataBeanListener.listQuanTJ;
        EasyExcel.read(fileName1, DemoData.class, demoDataListener).sheet(0).doReadSync();
        EasyExcel.read(fileName2, DateBean.class, dataBeanListener).sheet(0).doReadSync();
        handle();
        System.out.println("");
    }

    private static void handle() {
        for (DateBean bean : listChoose) {
            for (DemoData data : listAll) {
                float v = SimilarityRatio.getSimilarityRatio(bean.contractnName, data.contractnName);
                SetNode setNode = new SetNode();
                setNode.similarity = v;
                setNode.name = data.contractnName;
                setNode.num = data.num;
                bean.matchSet.add(setNode);
            }
            SetNode setNode1 = ((TreeSet<SetNode>) bean.matchSet).pollFirst();
            SetNode setNode2 = ((TreeSet<SetNode>) bean.matchSet).pollFirst();
            SetNode setNode3 = ((TreeSet<SetNode>) bean.matchSet).pollFirst();
            assert setNode1 != null;
            System.out.println("合同名称： " + bean.contractnName + " 匹配出的合同名称1：" + setNode1.name + " 相似度: " + setNode1.similarity + " 序号:：" + setNode1.num);
            assert setNode2 != null;
            System.out.println("合同名称： " + bean.contractnName + " 匹配出的合同名称2：" + setNode2.name + " 相似度: " + setNode2.similarity + " 序号:：" + setNode2.num);
            assert setNode3 != null;
            System.out.println("合同名称： " + bean.contractnName + " 匹配出的合同名称3：" + setNode3.name + " 相似度: " + setNode3.similarity + " 序号:：" + setNode3.num);
            System.out.println("===============================================================================");
        }

    }

    public static float similarCode(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        float length = Math.min(chars1.length, (float) chars2.length);
        Set<Character> set = new HashSet<>();
        for (Character character : chars1) {
            set.add(character);
        }
        for (Character character : chars2) {
            set.add(character);
        }
        float size = chars1.length + chars2.length - set.size();
        return size / length;
    }





    /**
     * 获取两字符串的相似度
     */


}
