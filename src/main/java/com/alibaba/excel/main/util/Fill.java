package com.alibaba.excel.main.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fill {

    public static void fill(List list, List<String> names) {
        Map<String, Object> map = new HashMap<>();
        for (Object o : list) {
            Field[] fields = o.getClass().getFields();
            for (Field field : fields) {
                String name = field.getName();
                if (!names.contains(name)) {
                    continue;
                }
                try {
                    Object value = field.get(o);
                    if (isNULL(value)) {
                        field.set(0, map.get(name));
                    } else {
                        map.put(name, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isNULL(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String && value.equals("")) {
            return true;
        }
        return false;
    }
}
