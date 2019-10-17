package com.alibaba.excel.main.statistical;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class DateBeanListener extends AnalysisEventListener<DateBean> {
    public List<DateBean> list = new ArrayList<>();
    String companyNmae = "";
    String cityName = "";

    @Override
    public void invoke(DateBean data, AnalysisContext context) {
        if (!JSON.toJSONString(data).equals("{}")) {
            if (!textisnull(data.companyNmae)) {
                companyNmae = data.companyNmae;
            }
            if (!textisnull(data.cityName)) {
                cityName = data.cityName;
            }
            data.companyNmae = textisnull(data.companyNmae) ? companyNmae : data.companyNmae;
            data.cityName = textisnull(data.cityName) ? cityName : data.cityName;
            list.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public boolean textisnull(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }
}
