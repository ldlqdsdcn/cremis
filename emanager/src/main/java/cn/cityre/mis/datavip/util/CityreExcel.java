package cn.cityre.mis.datavip.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/3/29.
 */
public class CityreExcel extends ExcelExport {

    private static int WHITE = IndexedColors.WHITE.getIndex();
    private static int GREY = IndexedColors.GREY_25_PERCENT.getIndex();
    private static int GREEN = IndexedColors.LIGHT_GREEN.getIndex();

    public CityreExcel(){
        super();
    }

    public CityreExcel(String fileName, int headerNum) throws InvalidFormatException, IOException {
        super(fileName, headerNum);
    }

    public CityreExcel(File file, int headerNum) throws InvalidFormatException, IOException {
        super(file, headerNum);
    }

    public CityreExcel(String fileName, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        super(fileName, headerNum, sheetIndex);
    }

    public CityreExcel(File file, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        super(file, headerNum, sheetIndex);
    }

    public CityreExcel(String fileName, InputStream is, int headerNum, int sheetIndex) throws InvalidFormatException, IOException {
        super(fileName, is, headerNum, sheetIndex);
    }

    public CityreExcel(String title, Map<String, String> headerMap) {
        super(title, headerMap);
    }

    public CityreExcel(String title, List<String> headerList) {
        super(title, headerList);
    }



    @Override
    protected Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont = wb.createFont();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        style.setFillForegroundColor((short) GREY);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("common", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("common"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("city", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("common"));
        style.setAlignment(CellStyle.ALIGN_LEFT);
        styles.put("fubiaoti", style);

        style = wb.createCellStyle();
        Font commonFont = wb.createFont();
        commonFont.setFontHeightInPoints((short) 10);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(commonFont);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        styles.put("ziti", style);
        return styles;
    }

    /**
     *
     * 设置表头信息
     * @Author	孙盛
     * @return  ExcelExport
     * @Date	2017年1月23日
     * 更新日志
     * 2017年1月23日 孙盛  首次创建
     *
     */
    @Override
    public ExcelExport setHead(List<NameCode> headList, boolean titleHead, String titleName){
        int rowNum = 0;
        int comNum = 0;
        int module = 0;
        Cell cell = null;
        //
        if(titleHead){
            setCellValue(getRow(0), 0, titleName);
            rowNum = 1;
        }

        Row currentRow = sheet.createRow(rowNum);
        Row currentRowNext =  sheet.createRow(rowNum+1);

//        titleRow.setHeight((short) 10);
        for (NameCode nameCode : headList) {
            List<NameCode> list = nameCode.getList();
            int num = 1;
            if(ObjectUtils.isEmpty(nameCode.getList())){//如果当前没有list说明是独立列
                sheet.addMergedRegion(new CellRangeAddress(currentRow.getRowNum(), currentRow.getRowNum()+1,
                        comNum, comNum));
                addCell(currentRowNext,comNum,"",null);
                module++;
                this.sheet.setColumnWidth(comNum,nameCode.getWidth());
            }else{
                num = nameCode.getList().size();
                sheet.addMergedRegion(new CellRangeAddress(currentRow.getRowNum(), currentRow.getRowNum(),
                        comNum, comNum+num-1));
                for(int _colnum = comNum+1;_colnum<comNum+num;_colnum++ ){//循环增加样式
                    addCell(currentRow,_colnum,"",null);
                }
                //循环内部结构
                for(int i = 0;i<list.size();i++ ){
                    NameCode _nameCode = list.get(i);
                    if(_nameCode.getName()!=null){
                        System.out.println(_nameCode.getWidth());
                        this.sheet.setColumnWidth(comNum+i,_nameCode.getWidth());
                    }
                    addCell(currentRowNext,comNum+i,_nameCode.getName(),styles.get("fubiaoti"));
                }
                module++;
            }
            addCell(currentRow,comNum,nameCode.getName(),null);
            comNum = comNum + num;
        }
        sheet.createFreezePane(0,2,0,2);
        return this;
    }

    public Cell addCell(Row row, int column, Object val,CellStyle style) {
        Cell cell = row.createCell(column);
        log.debug("result:"+row.getRowNum()+"___"+column);
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
            } else if (val instanceof Integer) {
                cell.setCellValue(Double.parseDouble(val.toString()));
            } else if (val instanceof Long) {
                cell.setCellValue(Double.parseDouble(val.toString()));
            } else if (val instanceof Double) {
                cell.setCellValue(Double.parseDouble(val.toString()));
            } else if (val instanceof Float) {
                cell.setCellValue(Double.parseDouble(val.toString()));
            } else if (val instanceof BigDecimal) {
                cell.setCellValue(Double.parseDouble(val.toString()));
            } else if (val instanceof Date) {
                cell.setCellValue((Date) val);
            }
            if(style==null){
                style = styles.get("city");
                cell.setCellStyle(style);
            }else{
                cell.setCellStyle(style);
            }
//            style.setFillForegroundColor((short) color);
//            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        } catch (Exception ex) {
            log.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        return cell;
    }

    public <E> ExcelExport setDataList(List<E> list, Map<String, Integer> dataMap,int firstRowNum, boolean hasRowNum) {
        rownum = firstRowNum;
        for (E e : list) {
            int colunm = 0;
            Row row = addRow();
            row.setHeightInPoints(13);
            if (hasRowNum) {
                addCell(row,colunm++,String.valueOf(rownum),styles.get("ziti"));
            }
            StringBuilder sb = new StringBuilder();
            for (String col : dataMap.keySet()) {
                try {
                    Object val = null;
                    int align = 0;
                    PropertyDescriptor des = new PropertyDescriptor(col, e.getClass());
                    Method method  = des.getReadMethod();
                    val = method.invoke(e);
//                    if(val==null){
//                        System.out.println("+++++++++++++++++++++++++++++"+col);
//                    }
                    if (dataMap.get(col) != null) {
                        align = dataMap.get(col).intValue();
                    }
                    addCell(row, colunm++, val, styles.get("ziti"));
                    sb.append(new StringBuilder().append(val).append(", ").toString());
                } catch (Exception ex) {
                    log.info(ex.toString());
                }
            }
            log.debug(new StringBuilder().append("Write success: [").append(row.getRowNum()).append("] ")
                    .append(sb.toString()).toString());
        }

        return this;
    }

}
