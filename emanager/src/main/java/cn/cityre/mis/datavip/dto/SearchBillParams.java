package cn.cityre.mis.datavip.dto;

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
}
