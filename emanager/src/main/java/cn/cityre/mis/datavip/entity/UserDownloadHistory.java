package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_download_history")
@Entity
@Getter
@Setter
public class UserDownloadHistory implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "userDownloadId",length = 10)
    private Integer userDownloadId;
    @Column(name = "orderCode",length = 64)
    @Length(max = 64,message = "")
    private String orderCode;
    @Column(name = "orderServiceId",length = 10)
    private Integer orderServiceId;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "updateTime")
    private Date updateTime;
}
