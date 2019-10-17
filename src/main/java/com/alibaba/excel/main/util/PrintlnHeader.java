package com.alibaba.excel.main.util;

public class PrintlnHeader {
    public static void main(String[] args) {
        String string = "类\t子模板编号\t子模板描述\t评审维度\t评审维度描述";
        String[] split = string.split("\t");
        System.out.println(split.length);
        int num =0;
        for (String s : split) {
//            System.out.println(" bean2List.add(new Bean2(\"" + s + "\",lists.get("+num++ +")));");
            System.out.println(" @ExcelProperty(\"" + s + "\")\n" +
                    " public   String " + s + ";");
        }
    }
}
