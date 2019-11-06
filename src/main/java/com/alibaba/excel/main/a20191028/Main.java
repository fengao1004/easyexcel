package com.alibaba.excel.main.a20191028;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.util.Reader;
import com.alibaba.excel.main.util.SimilarityRatio;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Main {
    String needPath = "D:\\smm\\excel\\20191028\\needbj.xls";
    String outPath = "D:\\smm\\excel\\20191028\\outbj.xls";
    String systemPath = "D:\\smm\\excel\\20191028\\system3.xls";
    private List<Bean1> systemList;
    Map<String, List<Bean2>> map = new LinkedHashMap<>();
    private ExcelWriter excelWriter;

    public static void main(String[] args) {
        Main main = new Main();
        main.getSystemExcel();
        main.readNeed(0, "土建总承包单位");
        main.readNeed(1, "精装修单位");
        main.readNeed(2, "景观单位");
        main.readNeed(3, "门窗单位");
        main.readNeed(4, "幕墙单位");
        main.out();

    }

    private void out() {
        excelWriter = EasyExcel.write(outPath, Bean2.class).build();
        out(0, "土建总承包单位");
        out(1, "精装修单位");
        out(2, "景观单位");
        out(3, "门窗单位");
        out(4, "幕墙单位");
        excelWriter.finish();
    }

    private void out(int i, String sheetName) {
        List<Bean2> bean2s = map.get(sheetName);
        int index = 1;
        for (Bean2 bean2 : bean2s) {
            fill(bean2, sheetName,index);
            index++;
        }
        WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).build();
        excelWriter.write(bean2s, writeSheet);
    }

    private void fill(Bean2 bean2, String name,int index) {
        TreeSet<TreeNode> set = new TreeSet();

        for (Bean1 bean1 : systemList) {
            float similarityRatio = SimilarityRatio.getSimilarityRatio(bean1.合同名称, bean2.合同名称);
            set.add(new TreeNode(bean1, similarityRatio));
        }
        TreeNode first = set.first();
        bean2.签约金额 = toPoint(first.bean.合同金额);
        bean2.最新合同金额 = toPoint(first.bean.合同最新金额);
        bean2.查询的合同名称 = first.bean.合同名称;
        bean2.查询的项目名称 = first.bean.项目名称;
        bean2.查询的城市公司 = first.bean.城市公司;
        bean2.查询的甲方名称 = first.bean.甲方名称;
        bean2.查询的乙方名称 = first.bean.乙方名称;
        printBean(name, bean2.合同名称, set,index);
    }

    private void printBean(String sheetName, String hetong, TreeSet<TreeNode> set,int index) {
        System.out.println(sheetName + "表第"+index+"个合同: " + hetong + " 的相似查询结果");
        for (int i = 0; i < 4; i++) {
            TreeNode treeNode = set.pollFirst();
            System.out.println("相似度排行: " + i);
            System.out.println("查询到的相似合同名称： " + treeNode.bean.合同名称);
            System.out.println("查询到的相似项目名称： " + treeNode.bean.项目名称);
            System.out.println("查询到的相似项目签约金额： " + treeNode.bean.合同金额);
            System.out.println("查询到的相似项目最新合同金额： " + treeNode.bean.合同最新金额);
        }
        System.out.println("=====================end=====================");
        System.out.println();
    }

    private String toPoint(Long 合同金额) {
        String str = 合同金额.toString();
        String substring1 = str.substring(0, str.length() - 4);
        String substring2 = str.substring(str.length() - 4, str.length());
        str = substring1 + "." + substring2;
        return str;
    }

    private void readNeed(int index, String sheetName) {
        Reader<Bean2> beanReader = new Reader<>(Bean2.class);
        List<Bean2> bean2s = beanReader.readExcel(needPath, index);
        map.put(sheetName, bean2s);
    }

    public void getSystemExcel() {
        Reader<Bean1> beanReader = new Reader<>(Bean1.class);
        systemList = beanReader.readExcel(systemPath, 0);
        int num = 0;
        for (Bean1 bean1 : systemList) {
            num++;
            System.out.println(bean1.合同最新金额 + "  " + bean1.合同金额 + " " + num);
        }
    }
}
