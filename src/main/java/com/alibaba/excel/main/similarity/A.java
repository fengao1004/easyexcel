package com.alibaba.excel.main.similarity;


import com.alibaba.excel.EasyExcel;

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
                float v = getSimilarityRatio(bean.contractnName, data.contractnName);
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


    private static int compare(String str, String target) {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {                       // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {                       // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) {                       // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */

    public static float getSimilarityRatio(String str, String target) {
        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
    }
}
