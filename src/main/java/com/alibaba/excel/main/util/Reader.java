package com.alibaba.excel.main.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Reader<T> extends AnalysisEventListener<T> {
    List<T> list;
    Class<T> tClass;

    public Reader(Class<T> tClass) {
        this.tClass = tClass;
        list = new ArrayList<T>();
    }

    public List<T> readExcel(String path, int sheetIndex) {
        EasyExcel.read(path, tClass, this).sheet(sheetIndex).doRead();
        List<T> l = new ArrayList<T>(list);
        list.clear();
        return l;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        String sheetName = context.readSheetHolder().getSheetName();
        String s = JSON.toJSONString(data);
        if (!s.equals("{}")) {
            list.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
