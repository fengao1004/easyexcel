package com.alibaba.excel.main.util;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeUtil extends AbstractMergeStrategy {
    private List<? extends Object> list;
    public List<MergeBean> mergeBeans = new ArrayList<>();
    private Map<Integer, String> map = new HashMap<>();
    private Map<Integer, String> mapLast = new HashMap<>();
    private Map<Integer, String> mapImportant = new HashMap<>();
    private Map<Integer, String> mapLastImportant = new HashMap<>();
    private Map<String, Boolean> mapImportantUpdate = new HashMap<>();

    public MergeUtil() {
    }

    public void ready(List<? extends Object> list) {
        this.list = list;
        map = new HashMap<>();
        mapLast = new HashMap<>();
        mapImportant = new HashMap<>();
        mapLastImportant = new HashMap<>();
        mapImportantUpdate = new HashMap<>();
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, int relativeRowIndex) {
        int rowIndex = cell.getRowIndex() - 1;
        for (MergeBean bean : mergeBeans) {
            if (bean.lineIndex == cell.getColumnIndex()) {
                String s = map.get(bean.lineIndex) == null ? "" : map.get(bean.lineIndex);
                String stringCellValue = cell.toString() == null ? "" : cell.toString();
                if (bean.startIndex == -1) {
                    bean.startIndex = 1;
                } else if (!s.equals(stringCellValue)) {
                    bean.endIndex = rowIndex;
                } else if (s.equals(stringCellValue)) {
                    if (bean.importantLineInex != -1 && bean.importantLineInex < bean.lineIndex) {
                        String s1 = mapImportant.get(bean.importantLineInex) == null ? "" : mapImportant.get(bean.importantLineInex);
                        String s2 = mapLastImportant.get(bean.importantLineInex) == null ? "" : mapLastImportant.get(bean.importantLineInex);
                        if (!(s1 + s).equals(s2 + s)) {
                            bean.endIndex = rowIndex;
                        } else {
                            if (rowIndex == list.size() - 1) {
                                bean.endIndex = rowIndex + 1;
                            }
                        }
                    } else {
                        if (rowIndex == list.size() - 1) {
                            bean.endIndex = rowIndex + 1;
                        }
                    }
                }
                if (bean.endIndex != -1 && bean.startIndex < bean.endIndex) {
                    System.out.println("合并一次:start_ " + bean.startIndex + ";end_ " + bean.endIndex + " column_" + cell.getColumnIndex() + " row_" + cell.getRowIndex());
                    sheet.addMergedRegion(new CellRangeAddress(bean.startIndex,
                            bean.endIndex, cell.getColumnIndex(), cell.getColumnIndex()));
                    if (bean.followIndex != null && bean.followIndex.size() > 0) {
                        for (Integer integer : bean.followIndex) {
                            sheet.addMergedRegion(new CellRangeAddress(bean.startIndex,
                                    bean.endIndex, integer, integer));
                        }
                    }
                    bean.startIndex = bean.endIndex + 1;
                    bean.endIndex = -1;
                } else if (bean.endIndex != -1) {
                    bean.startIndex = bean.endIndex + 1;
                    bean.endIndex = -1;
                }
                s = stringCellValue;
                mapLast.put(cell.getColumnIndex(), map.get(cell.getColumnIndex()) == null ? "" : map.get(cell.getColumnIndex()));
                map.put(cell.getColumnIndex(), s);
            }
            if (bean.importantLineInex == cell.getColumnIndex()) {
                String stringCellValue = cell.toString() == null ? "" : cell.toString();
                if (bean.importantLineInex > bean.lineIndex) {
                    String s1 = mapImportant.get(bean.importantLineInex) == null ? "" : mapImportant.get(bean.importantLineInex);
                    String sa = map.get(bean.lineIndex) == null ? "" : map.get(bean.lineIndex);
                    String sb = mapLast.get(bean.lineIndex) == null ? "" : mapLast.get(bean.lineIndex);
                    if (!(s1 + sb).equals(stringCellValue + sa)) {
                        bean.endIndex = rowIndex;
                    } else {
                        if (rowIndex == list.size() - 1) {
                            bean.endIndex = rowIndex + 1;
                        }
                    }
                }
                String s = mapImportant.get(bean.importantLineInex) == null ? "" : mapImportant.get(bean.importantLineInex);
                if (mapImportantUpdate.get(cell.getColumnIndex() + "&" + cell.getRowIndex()) == null || !mapImportantUpdate.get(cell.getColumnIndex() + "&" + cell.getRowIndex())) {
                    mapLastImportant.put(bean.importantLineInex, s);
                    mapImportant.put(bean.importantLineInex, cell.toString() == null ? "" : cell.toString());
                    mapImportantUpdate.put(cell.getColumnIndex() + "&" + cell.getRowIndex(), true);
                }

                if (bean.endIndex != -1 && bean.startIndex < bean.endIndex) {
                    sheet.addMergedRegion(new CellRangeAddress(bean.startIndex,
                            bean.endIndex, bean.lineIndex, bean.lineIndex));
                    bean.startIndex = bean.endIndex + 1;
                    bean.endIndex = -1;
                } else if (bean.endIndex != -1) {
                    bean.startIndex = bean.endIndex + 1;
                    bean.endIndex = -1;
                }
            }
        }
    }
}
