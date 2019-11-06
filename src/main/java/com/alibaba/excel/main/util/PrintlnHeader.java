package com.alibaba.excel.main.util;

public class PrintlnHeader {
    public static void main(String[] args) {
        String string = "产品编码\t手机号码\t终端品牌\t终端型号";
        String[] split = string.split("\t");
        System.out.println(split.length);
        int num = 0;
        for (String s : split) {
//            System.out.println(" bean2List.add(new Bean2(\"" + s + "\",lists.get("+num++ +")));");
            System.out.println(" @ExcelProperty(index=" + num + ",value=\"" + s + "\")\n" +
                    " public   String " + s + ";");
            num++;
        }
    }
}
