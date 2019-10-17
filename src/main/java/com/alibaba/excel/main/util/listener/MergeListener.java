package com.alibaba.excel.main.util.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.main.util.bean.MergeTestBean;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.Map;

public class MergeListener extends AnalysisEventListener<MergeTestBean> {

    @Override
    public void invoke(MergeTestBean data, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
