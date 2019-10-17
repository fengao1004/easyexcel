package com.alibaba.excel.main.statistical;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class MyLoopMergeStrategy extends AbstractMergeStrategy {
    String city_gongsi;
    int city_gongsi_index = 1;
    String city;
    int city_index = 1;
    String htgong_num;
    int htgong_num_index = 1;
    String gys;
    int gys_index = 1;
    String name;
    int name_index = 1;
    private final List<RowBeanA> data;
    boolean change = false;
    int change_index = 1;
    int change_end_index = 1;
    boolean change1 = false;
    int change_index1 = 1;
    int change_end_index1 = 1;

    public MyLoopMergeStrategy(List<RowBeanA> data) {
        this.data = data;
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, int relativeRowIndex) {
        System.out.println(cell.getRowIndex() + "_" + cell.getColumnIndex());
        if (cell.getColumnIndex() == 3) {
            int rowIndex = cell.getRowIndex() - 1;
            if (rowIndex < data.size() - 1) {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (gys == null || gys.equals("")) {
                    gys = rowBeanA.name;
                } else if (!gys.equals(rowBeanA.name)) {
                    if (gys_index < rowIndex) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(gys_index,
                                rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                    }
                    gys_index = rowIndex + 1;
                }
                gys = rowBeanA.name;
            } else {
                if (gys_index < rowIndex + 1) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(gys_index,
                            rowIndex + 1, cell.getColumnIndex(), cell.getColumnIndex());
                    sheet.addMergedRegion(cellRangeAddress);
                }

            }
        }
        if (cell.getColumnIndex() == 0) {
            int rowIndex = cell.getRowIndex() - 1;
            if (rowIndex < data.size() - 1) {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (city_gongsi == null || city_gongsi.equals("")) {
                    city_gongsi = rowBeanA.teamworkModel;
                } else if (!city_gongsi.equals(rowBeanA.teamworkModel)) {
                    if (city_gongsi_index < rowIndex) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(city_gongsi_index,
                                rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                    }
                    city_gongsi_index = rowIndex + 1;
                }
                city_gongsi = rowBeanA.teamworkModel;
            } else {
                if (city_gongsi_index < rowIndex + 1) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(city_gongsi_index,
                            rowIndex + 1, cell.getColumnIndex(), cell.getColumnIndex());
                    sheet.addMergedRegion(cellRangeAddress);
                }

            }
        }
        if (cell.getColumnIndex() == 1) {
            int rowIndex = cell.getRowIndex() - 1;
            if (rowIndex < data.size() - 1) {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (htgong_num == null || htgong_num.equals("")) {
                    htgong_num = rowBeanA.contractNum;
                } else if (!htgong_num.equals(rowBeanA.contractNum)) {
                    if (htgong_num_index < rowIndex) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(htgong_num_index,
                                rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                        change1 = true;
                    }
                    change_index1 = htgong_num_index;
                    htgong_num_index = rowIndex + 1;
                    change_end_index1 = rowIndex;
                }
                htgong_num = rowBeanA.contractNum;
            } else {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (htgong_num.equals(rowBeanA.contractNum)){
                    if (htgong_num_index < rowIndex + 1) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(htgong_num_index,
                                rowIndex + 1, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                        change1 = true;
                        change_index1 = htgong_num_index;
                        change_end_index1 = rowIndex + 1;
                    }
                }else {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(htgong_num_index,
                            rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                    sheet.addMergedRegion(cellRangeAddress);
                    change1 = true;
                    change_index1 = htgong_num_index;
                    change_end_index1 = rowIndex;
                }
            }
        }
        if (cell.getColumnIndex() == 4) {
            int rowIndex = cell.getRowIndex() - 1;
            if (rowIndex < data.size() - 1) {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (city == null || city.equals("")) {
                    city = rowBeanA.cityName + "_" + rowBeanA.name;
                } else if (!city.equals(rowBeanA.cityName + "_" + rowBeanA.name)) {
                    if (city_index < rowIndex) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(city_index,
                                rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                        change = true;
                    }
                    change_index = city_index;
                    change_end_index = rowIndex;
                    city_index = rowIndex + 1;
                }
                city = rowBeanA.cityName + "_" + rowBeanA.name;
            } else {
                RowBeanA rowBeanA = data.get(rowIndex);
                if (city.equals(rowBeanA.cityName + "_" + rowBeanA.name)) {
                    if (city_index < rowIndex + 1) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(city_index,
                                rowIndex + 1, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                        change = true;
                        change_index = city_index;
                        change_end_index = rowIndex + 1;
                    }
                } else {
                    if (city_index < rowIndex) {
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(city_index,
                                rowIndex, cell.getColumnIndex(), cell.getColumnIndex());
                        sheet.addMergedRegion(cellRangeAddress);
                        change = true;
                        change_index = city_index;
                        change_end_index = rowIndex;
                    }
                }
            }
        }
        if (cell.getColumnIndex() == 5) {
            if (change) {
                change = false;
                CellRangeAddress cellRangeAddress = new CellRangeAddress(change_index,
                        change_end_index, cell.getColumnIndex(), cell.getColumnIndex());
                sheet.addMergedRegion(cellRangeAddress);
            }
        }

        if (cell.getColumnIndex() == 2) {
            if (change1) {
                change1 = false;
                CellRangeAddress cellRangeAddress = new CellRangeAddress(change_index1,
                        change_end_index1, cell.getColumnIndex(), cell.getColumnIndex());
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
    }
}
