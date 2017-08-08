package cn.cityre.mis.ifmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/10.
 */
@Table(name = "v2017_api_key")
@Entity
@Getter
@Setter
public class MisApiKeyPo implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;
    @Column(name = "apiKey", unique = true, length = 64)
    @Length(max = 64, message = "base.v2017.apikey.apikey.length")
    private String apiKey;
    @Column(name = "scopeMask", length = 10)
    private Integer scopeMask;
    @Column(name = "limitMap", length = 1024)
    @Length(max = 1024, message = "base.v2017.apikey.limitMap.length")
    private String limitMap;
    @Column(name = "blackList", length = 1024)
    @Length(max = 1024, message = "base.v2017.apikey.blackList.length")
    private String blackList;
    @Column(name = "whiteList", length = 1024)
    @Length(max = 1024, message = "base.v2017.apikey.whiteList.length")
    private String whiteList;
    @Column(name = "note", length = 64)
    @Length(max = 64, message = "base.v2017.apikey.note.length")
    private String note;
    @Column(name = "productCode", length = 32)
    @Length(max = 32,message = "base.v2017.apikey.productCode.length")
    private String productCode;
    @Column(name = "version", length = 16)
    @Length(max =16,message = "base.v2017.apikey.version.length")
    private String version;
    @Column(name = "isValid", length = 4)
    private Byte isValid;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "expireTime")
    private Date expireTime;
}
