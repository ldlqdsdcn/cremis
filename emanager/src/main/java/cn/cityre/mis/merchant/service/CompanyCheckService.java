package cn.cityre.mis.merchant.service;


import cn.cityre.mis.merchant.entity.po.CoCheck;
import com.dsdl.eidea.util.Page;

import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
public interface CompanyCheckService {
    public List<CoCheck> companyCheck(String citypinyin, String status, String companyName, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, Page pager);
    public String checkAll(String idStr, int certification, String uid);
    public String checkone(String cocode, int certification, String uid);

    public void commonUpdate(String cocode,String couid);
}
