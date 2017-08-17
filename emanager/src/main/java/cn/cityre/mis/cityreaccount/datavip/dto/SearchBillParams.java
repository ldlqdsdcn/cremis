package cn.cityre.mis.cityreaccount.datavip.dto;

import com.dsdl.eidea.core.params.QueryParams;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by cityre on 2017/8/16.
 */
@Data
public class SearchBillParams implements Serializable {
    private String uid;
    private String billCode;
    private String bigBillCode;
    private String alipayBillCode;
    private String typeCode;
    private Integer payFlag;
    private String invoiceNoFlag;
    private String postInvoiceFlag;
    private String postTypeCode;
    private String invoiceNo;
    private String invoiceType;
    private QueryParams queryParams;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBigBillCode() {
        return bigBillCode;
    }

    public void setBigBillCode(String bigBillCode) {
        this.bigBillCode = bigBillCode;
    }

    public String getAlipayBillCode() {
        return alipayBillCode;
    }

    public void setAlipayBillCode(String alipayBillCode) {
        this.alipayBillCode = alipayBillCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(Integer payFlag) {
        this.payFlag = payFlag;
    }

    public String getInvoiceNoFlag() {
        return invoiceNoFlag;
    }

    public void setInvoiceNoFlag(String invoiceNoFlag) {
        this.invoiceNoFlag = invoiceNoFlag;
    }

    public String getPostInvoiceFlag() {
        return postInvoiceFlag;
    }

    public void setPostInvoiceFlag(String postInvoiceFlag) {
        this.postInvoiceFlag = postInvoiceFlag;
    }

    public String getPostTypeCode() {
        return postTypeCode;
    }

    public void setPostTypeCode(String postTypeCode) {
        this.postTypeCode = postTypeCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
    }
}
