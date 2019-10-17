package com.alibaba.excel.main.readphonenumber;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.main.similarity.DemoData;
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
public class DemoDataListener extends AnalysisEventListener<Bean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    public List<Bean> list = new ArrayList<Bean>();


    @Override
    public void invoke(Bean data, AnalysisContext context) {
        if (JSON.toJSONString(data).equals("{}")) {
            return;
        }
//        System.out.println("<item>"+data.phoneNumber+"</item>");
        System.out.print("\""+data.phoneNumber+"\",");
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
