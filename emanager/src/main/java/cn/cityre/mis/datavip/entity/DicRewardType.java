package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_rewardtype")
@Setter
@Getter
@Entity
public class DicRewardType implements java.io.Serializable {
    @Column(name = "rewardcode", length = 10)
    @Length(max = 10, message = "")
    private String rewardCode;
    @Column(name = "rewardname", length = 20)
    @Length(max = 20, message = "")
    private String rewardName;
}
