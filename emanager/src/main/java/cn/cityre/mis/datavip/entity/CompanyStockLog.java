package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 额度日志表
 */
@Table(name = "company_stock_log")
@Entity
@Getter
@Setter
public class CompanyStockLog implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 11,nullable = false,unique = true)
    private Integer id;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String companyCode;
    @Column(name = "numamount",length = 9)
    private Integer numAmount;
    @Column(name = "datestart")
    private Date dateStart;
    @Column(name = "dateend")
    private Date dateEnd;
    @Column(name = "offeruid",length = 50)
    @Length(max = 50,message = "")
    private String offerUid;
    @Column(name = "offertime")
    private Date offerTime;
}
