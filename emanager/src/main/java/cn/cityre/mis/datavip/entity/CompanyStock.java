package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 公司额度表
 */
@Table(name = "company_stock")
@Getter
@Setter
@Entity
public class CompanyStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String  companyCode;
    /**
     * 额度总额
     */
    @Column(name = "numamount",length = 9)
    private Integer numAmount;
    /**
     * 额度余额
     */
    @Column(name = "numbalance",length = 9)
    private Integer numBalance;
    @Column(name = "datestart")
    private Date  dateStart;
    @Column(name = "dateEnd")
    private Date dateEnd;
    @Column(name = "offeruid",length = 50)
    @Length(max = 50,message = "")
    private String offerUid;
    @Column(name = "offertime")
    private Date offerTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
}
