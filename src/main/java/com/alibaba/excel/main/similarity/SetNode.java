package com.alibaba.excel.main.similarity;

public class SetNode implements Comparable<SetNode> {
    public String name;
    public String num;
    public float similarity;

    @Override
    public int compareTo(SetNode o) {
        return o.similarity > similarity ? 1 : -1;
    }
}
