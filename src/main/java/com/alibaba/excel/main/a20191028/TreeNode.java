package com.alibaba.excel.main.a20191028;

import java.util.Comparator;

public class TreeNode implements Comparable<TreeNode> {
    Bean1 bean;
    float point;

    public TreeNode(Bean1 bean, float point) {
        this.bean = bean;
        this.point = point;
    }


    @Override
    public int compareTo(TreeNode o) {
        return Float.compare(o.point, this.point);
    }
}
