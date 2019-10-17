package com.alibaba.excel.main.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.main.a20190916.step1.OutBean;
import com.alibaba.excel.main.statistical.DateBean;
import com.alibaba.excel.main.statistical.MyLoopMergeStrategy;
import com.alibaba.excel.main.tongji.ReaderBean;
import com.alibaba.excel.main.util.bean.MergeTestBean;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.HashMap;
import java.util.List;

public class Test {
    static String path = "D:\\smm\\excel\\exceltest\\merge.xls";
    static String outPath = "D:\\smm\\excel\\exceltest\\mergeOut.xls";

    public static void main(String[] args) {
        Reader<MergeTestBean> readerBeanReader = new Reader<>(MergeTestBean.class);
        List<MergeTestBean> mergeTestBeans = readerBeanReader.readExcel(path, 0);
        ExcelWriter excelWriter = EasyExcel.write(outPath, MergeTestBean.class).build();
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("幕墙", mergeTestBeans.size());
        MergeUtil mergeUtil = new MergeUtil();
        MergeBean mergeType = new MergeBean();
        mergeType.lineIndex = 0;
        MergeBean mergeName = new MergeBean();
        mergeName.lineIndex = 2;
        mergeName.followIndex.add(5);
        MergeBean mergeCity1 = new MergeBean();
        mergeCity1.lineIndex = 1;
        mergeCity1.importantLineInex = 2;
        MergeBean mergeCity2 = new MergeBean();
        mergeCity2.lineIndex = 3;
        mergeCity2.importantLineInex = 2;
        mergeUtil.mergeBeans.add(mergeType);
        mergeUtil.mergeBeans.add(mergeName);
        mergeUtil.mergeBeans.add(mergeCity1);
        mergeUtil.mergeBeans.add(mergeCity2);
        WriteSheet writeSheet4 = EasyExcel.writerSheet(0, "幕墙").registerWriteHandler(mergeUtil).build();
        excelWriter.write(mergeTestBeans, writeSheet4);
        excelWriter.finish();
    }
}
