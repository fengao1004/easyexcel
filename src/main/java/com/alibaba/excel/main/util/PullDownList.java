package com.alibaba.excel.main.util;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * 自定义拦截器.对第一列第一行和第二行的数据新增下拉框，显示 测试1 测试2
 *
 * @author Jiaju Zhuang
 */
public class PullDownList implements SheetWriteHandler {

    public int size;

    public PullDownList() {
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

        // 区间设置 第一列第一行和第二行的数据。由于第一行是头，所以第一、二行的数据实际上是第二三行
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, size, 2, 2);
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[]{"区采单位", "城采单位", "单项采购单位"});
        DataValidation dataValidation1 = helper.createValidation(constraint, cellRangeAddressList);

        cellRangeAddressList = new CellRangeAddressList(1, size, 12, 12);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"本城市储备", "引用其他城市单位", "引用区采单位", "引用融创其他区域单位"});
        DataValidation dataValidation2 = helper.createValidation(constraint, cellRangeAddressList);


        cellRangeAddressList = new CellRangeAddressList(1, size, 14, 14);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"招标", "直接签署", "直接委托", "比价"});
        DataValidation dataValidation3 = helper.createValidation(constraint, cellRangeAddressList);

        cellRangeAddressList = new CellRangeAddressList(1, size, 18, 18);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"采购进行中", "已定标", "已签约", "已完工", "已交付", "已结算", "保修期已满"});
        DataValidation dataValidation4 = helper.createValidation(constraint, cellRangeAddressList);


        cellRangeAddressList = new CellRangeAddressList(1, size, 25, 25);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"本城市单项目合作", "本城市多项目合作", "本城市不再合作"});
        DataValidation dataValidation5 = helper.createValidation(constraint, cellRangeAddressList);

        cellRangeAddressList = new CellRangeAddressList(1, size, 26, 26);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"是", "否"});
        DataValidation dataValidation6 = helper.createValidation(constraint, cellRangeAddressList);

        cellRangeAddressList = new CellRangeAddressList(1, size, 27, 27);
        helper = writeSheetHolder.getSheet().getDataValidationHelper();
        constraint = helper.createExplicitListConstraint(new String[]{"是", "否"});
        DataValidation dataValidation7 = helper.createValidation(constraint, cellRangeAddressList);

        writeSheetHolder.getSheet().addValidationData(dataValidation1);
        writeSheetHolder.getSheet().addValidationData(dataValidation2);
        writeSheetHolder.getSheet().addValidationData(dataValidation3);
        writeSheetHolder.getSheet().addValidationData(dataValidation4);
        writeSheetHolder.getSheet().addValidationData(dataValidation5);
        writeSheetHolder.getSheet().addValidationData(dataValidation6);
        writeSheetHolder.getSheet().addValidationData(dataValidation7);


    }
}
