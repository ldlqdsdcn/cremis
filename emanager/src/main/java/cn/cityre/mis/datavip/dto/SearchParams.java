package cn.cityre.mis.datavip.dto;

import com.dsdl.eidea.core.params.QueryParams;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by cityre on 2017/8/16.
 */
@Data
@Getter
@Setter
public class SearchParams implements Serializable {
    private String uid;
    private String userType;
    private String payTel;
    private String payFlag;
    private String newUser;
    private String regStartTime;
    private String regEndTime;
    private String serviceStartTime;
    private String serviceEndTime;
    private QueryParams queryParams;
}
