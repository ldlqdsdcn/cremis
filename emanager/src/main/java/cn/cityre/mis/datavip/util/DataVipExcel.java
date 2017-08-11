package cn.cityre.mis.datavip.util;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/11.
 */
public class DataVipExcel extends ExcelExport {
    @Override
    public <E> ExcelExport setDataList(List<E> list, Map<String, Integer> dataMap, int firstRowNum, boolean hasRowNum) {
        return null;
    }

    @Override
    public ExcelExport setHead(List<NameCode> headList, boolean titleHead, String titleName) {
        return null;
    }
}
