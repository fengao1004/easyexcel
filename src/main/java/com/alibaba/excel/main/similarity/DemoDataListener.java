package com.alibaba.excel.main.similarity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    public List<DemoData> list = new ArrayList<DemoData>();
    int num = 0;
    public List<DemoData> listQuanTJ = new ArrayList<>();
    public List<DemoData> listQuanJZ = new ArrayList<>();
    public List<DemoData> listQuanJG = new ArrayList<>();
    public List<DemoData> listQuanMQ = new ArrayList<>();
    public List<DemoData> listQuanMC = new ArrayList<>();
    String number = "";

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        if (JSON.toJSONString(data).equals("{}")) {
            return;
        }
        if (data.num != null && data.num.length() > 0) {
            number = data.num;
        } else {
            data.num = number;
        }
        listQuanTJ.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        LOGGER.info("存储数据库成功！");
    }
}
