package cn.cityre.edi.mis.base.entity.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/10.
 */
@Table(name = "v2017_api_key",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisApiKeyPo implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false,length = 10)
    private Integer id;
    @Column(name = "apiKey",unique = true,length = 64)
    private String apiKey;
    @Column(name = "scopeMask",length = 10)
    private Integer scopeMask;
    @Column(name = "limitMap",length = 1024)
    private String limitMap;
    @Column(name = "blackList",length = 1024)
    private String blackList;
    @Column(name = "whiteList",length = 1024)
    private String whiteList;
    @Column(name="note",length = 64)
    private String note;
    @Column(name = "productCode",length = 32)
    private String productCode;
    @Column(name = "version",length = 16)
    private String version;
    @Column(name = "isValid",length = 4)
    private Byte isValid;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "expireTime")
    private Date expireTime;

}
