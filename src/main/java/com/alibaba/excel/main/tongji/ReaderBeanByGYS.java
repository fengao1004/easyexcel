package com.alibaba.excel.main.tongji;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ReaderBeanByGYS {
    String gysName;

    List<String> citys;

    Map<String, List<String>> map;

    public ReaderBeanByGYS() {
        citys = new ArrayList();
        map = new HashMap<String, List<String>>();
    }

    public static List<ReaderBeanByGYS> transitionList(List<ReaderBean> list) {
        Map<String, ReaderBeanByGYS> map1 = new HashMap<String, ReaderBeanByGYS>();
        for (ReaderBean bean : list) {
            ReaderBeanByGYS readerBeanByGYS = map1.get(bean.gysName);
            if (readerBeanByGYS == null) {
                readerBeanByGYS = new ReaderBeanByGYS();
                map1.put(bean.gysName, readerBeanByGYS);
                readerBeanByGYS.gysName = bean.gysName;
            }
            if (!readerBeanByGYS.citys.contains(bean.city)) {
                readerBeanByGYS.citys.add(bean.city);
            }
            List<String> strings = readerBeanByGYS.map.get(bean.city);
            if (strings == null) {
                strings = new ArrayList<String>();
                readerBeanByGYS.map.put(bean.city, strings);
            }
            strings.add(bean.htName);
        }
        List<ReaderBeanByGYS> list1 = new ArrayList<ReaderBeanByGYS>();
        String gysName = "";
        for (ReaderBean bean : list) {
            if (gysName.equals("") || !gysName.equals(bean.gysName)) {
                gysName = bean.gysName;
                list1.add(map1.get(bean.gysName));
            }
        }
        return list1;
    }

}
