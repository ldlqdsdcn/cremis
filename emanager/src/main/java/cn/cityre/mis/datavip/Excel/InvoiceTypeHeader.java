package cn.cityre.mis.datavip.Excel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cityre on 2017/8/16.
 */
public class InvoiceTypeHeader implements ExcelHeader {
    @Override
    public List<String[]> getHeadNames() {
        List<String[]> headNames = new ArrayList<String[]>();
        headNames.add(new String[] { "帐号", "姓名", "用户类型", "注册时间","电话","Email","状态","账单号","账单状态","账单金额","服务类型","城市","查询级别","名称","附近","租售","服务开始时间","服务结束时间","发票抬头"});
        return headNames;
    }

    @Override
    public List<String[]> getFieldNames() {
        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] { "uid", "userName",  "userTypeName","regTime","payTel","email","flag", "payAmount", "note", "billsCityCode","pLevel","contentName","pGps","dealType","startTime","endTime","invoiceTitle"});
        return fieldNames;
    }
}
