package com.alibaba.excel.main.readphonenumber;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.main.similarity.DemoData;

public class Main {
    public static void main(String[] args) {
        String path = "D:\\资料\\vivo.xls";
        EasyExcel.read(path, Bean.class, new DemoDataListener()).sheet(0).doReadSync();
    }
}
