package cn.cityre.mis.datavip.converter;

import cn.cityre.mis.datavip.del.InvoiceType;

import javax.persistence.AttributeConverter;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by cityre on 2017/8/15.
 */
public class InvoiceTypeConverter implements AttributeConverter<String,Integer> {
    @Override
    public Integer convertToDatabaseColumn(String s) {
        Integer key = null;
        for (InvoiceType invoiceType :InvoiceType.values()){
            if (invoiceType.getValue().equals(s)){
                key= invoiceType.getKey();
            }
        }
        return key;
    }

    @Override
    public String convertToEntityAttribute(Integer integer) {
        String value=null;
        for (InvoiceType invoiceType: InvoiceType.values()){
            if (invoiceType.getKey()==integer){
                value = invoiceType.getValue();
            }
        }
        return value;
    }
}
