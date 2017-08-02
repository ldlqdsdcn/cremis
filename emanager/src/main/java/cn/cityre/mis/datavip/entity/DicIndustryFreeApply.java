package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 服务免费试用所属行业字典表
 */
@Table(name = "dic_industry_freeapply")
@Entity
@Getter
@Setter
public class DicIndustryFreeApply implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 11,nullable = false,unique = true)
    private Integer id;
    @Column(name = "industry_code",length = 10)
    @Length(max = 10,message = "")
    private String industryCode;
    @Column(name = "industry_name",length = 50)
    @Length(max = 50,message = "")
    private String industryname;
    @Column(name = "updatetime")
    private Date udpateTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
}
