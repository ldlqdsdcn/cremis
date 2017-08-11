package cn.cityre.mis.datavip.util;


import com.dsdl.eidea.devs.db.ExcelStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by cityre on 2017/8/11.
 */
public class ExcelTemplate {
    protected Workbook wb;
    private HSSFWorkbook hwb;


    /**
     * 根据路径读取模板
     *
     * @param path
     * @return
     */
    public  ExcelTemplate getTemplateByPath(String path) {
        try {
            wb = WorkbookFactory.create(ExcelTemplate.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 根据路径输出文件
     * @param path
     */
    public void exportFile(String path)  {
        FileOutputStream fileOutputStream = null;
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加单元格
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @param cellStyle
     */
    public Cell addCell(Sheet sheet, int rowIndex, int colIndex, CellStyle cellStyle){
        Row row = sheet.getRow(rowIndex);
        if (row == null){
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(colIndex);
        if (cell == null){
            cell=row.createCell(colIndex);
        }
        /*设置单元格样式*/
        cell.setCellStyle(cellStyle);
        return cell;
    }

    /**
     * 设置表头
     * @param headerList
     * @param sheetName
     * @return
     */
    public void setHeader(List<String> headerList,String sheetName){
        Sheet sheet = wb.createSheet(sheetName);
        /*表头默认在第一列*/
        Row headRow = sheet.createRow(0);
        for(int i=0;i<=headerList.size();i++){
            Cell cell = headRow.createCell(i);
            cell.setCellStyle(ExcelStyle.getHeadStyle());
            cell.setCellValue(headerList.get(i));
        }
    }

}
