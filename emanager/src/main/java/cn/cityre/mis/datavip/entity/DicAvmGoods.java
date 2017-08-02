package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_avmgoods")
@Entity
@Getter
@Setter
public class DicAvmGoods implements java.io.Serializable{
    @Id
    @Column(name = "id",nullable = false,unique = true,length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goodsid",length =50 )
    private String goodsId;
    @Column(name = "goodsname",length = 200)
    @Length(max = 200,message = "")
    private String goodsName;
    /**
     * 商品类型： 1，个人商品   2，公司商品
     */
    @Column(name = "goodstype",length = 1)
    private Integer goodsType;
    /**
     * 服务范围标识 1=本地，2=外地，3=全国
     * 默认  3
     */
    @Column(name = "scopeflag",length = 10)
    @Length(max = 10,message = "")
    private String scopeFlag;
    /**
     * 奖励类型
     */
    @Column(name = "rewardtype",length = 10)
    @Length(max = 10,message = "")
    private String rewardType;
    /**
     * 评估次数
     */
    @Column(name = "numtimes",length = 5)
    private Integer numTimes;
    @Column(name = "adduid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "updatedate")
    private Date updateDate;
    @Column(name = "enabledflag")
    private String enabledFlag;
}
