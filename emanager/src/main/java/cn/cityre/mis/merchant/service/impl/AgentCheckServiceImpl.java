package cn.cityre.mis.merchant.service.impl;


import cn.cityre.edi.mis.base.util.CityDataSourceUtil;
import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.edi.mis.base.util.PropertyAccessor;
import cn.cityre.mis.merchant.dao.CoUserCheckDao;
import cn.cityre.mis.merchant.dao.UserListDao;
import cn.cityre.mis.merchant.entity.po.CoUser;
import cn.cityre.mis.merchant.entity.po.UserList;
import cn.cityre.mis.merchant.service.AgentCheckService;
import com.dsdl.eidea.util.LikeUtil;
import com.dsdl.eidea.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "agentCheckService")
public class AgentCheckServiceImpl implements AgentCheckService {
    @Autowired
    private CoUserCheckDao coUserCheckDao;
    @Autowired
    private UserListDao userListDao;
    /**
     * 经纪人认证审核照片
     * @param status
     * @param agentName
     * @param agentUid
     * @param subStartDate
     * @param subEndDate
     * @param chkStartDate
     * @param chkEndDate
     * @param pager
     * @return
     */
    public List<CoUser> agentCheck(String citypinyin, String status, String agentName,String agentUid, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, Page pager){

        List<CoUser> list = new ArrayList<CoUser>();
        Map<String,Object> map = new HashMap<String,Object>();
        if(status != null && !status.equals("")){
            map.put("status", Integer.parseInt(status));
        }
        map.put("agentName", LikeUtil.getLike(agentName));
        map.put("agentUid", LikeUtil.getLike(agentUid));
        map.put("subStartDate",subStartDate);
        map.put("subEndDate",subEndDate);
        map.put("chkStartDate",chkStartDate);
        map.put("chkEndDate",chkEndDate);
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        Integer count =coUserCheckDao.selectCoUserCount(map);
        pager.setTotalCount(count);
        if(count >0){
            list = coUserCheckDao.selectCoUserList(map);
            for(CoUser c :list){
                String url = null;
                try {
                    url = PropertyAccessor.getProperty("misImageUrl")+citypinyin+"/images/certification/"+c.getCertPhoto();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c.setCertPhoto(url);
            }
        }
        return list;
    }
    public String checkAll(String idStr,int certification,String sysuid){
        String[] array=idStr.split(",");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("certification",certification);
        map.put("uid",sysuid);
        map.put("list",array);
        coUserCheckDao.updateCoUserAll(map);
        //公司实名认证通过后，用户资料同时通过审核，如果已通过电话认证则不置状态
        if(certification == 1){
                for(int i=0;i<array.length;i++){
                    CoUser c = coUserCheckDao.selectCoUserById(Integer.parseInt(array[i]));
                    if(c.getUid() != null ){
                       UserList u = userListDao.selectUserlistByUid(c.getUid());
                       if(u != null ){
                            if(u.getUserlist_flag()!=null && u.getUserlist_flag() != 1){
                                map.put("userlistuid",c.getUid());
                                userListDao.updateUserlist_flag_uid(map);
                            }
                       }
                    }
                }

        }
        return "OK";
    }
    public String checkone(String uid, int certification, String sysuid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("certification",certification);
        map.put("uid",sysuid);
        map.put("cocode",uid);
        coUserCheckDao.updateCoUserByUid(map);
        if(certification == 1){
                UserList u = userListDao.selectUserlistByUid(uid);
                if(u != null ){
                    if(u.getUserlist_flag()!=null && u.getUserlist_flag() != 1){
                        map.put("userlistuid",uid);
                        userListDao.updateUserlist_flag_uid(map);
                    }
                }
        }
        return "OK";
    }

    /**
     * 个人信息
     * @param uid
     * @param citypinyin
     * @param citycode
     * @return
     */
    public  UserList userinfo(String uid, String citypinyin, String citycode){
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        UserList u = userListDao.selectUserInfo(uid);
        if(u != null){
            if(StringUtils.isNotEmpty(u.getHeadportrait())){
                int cnt =userListDao.selectUserHouseSourceCnt(uid);
                u.setSourceCnt(cnt);
                try {
                    String url =  PropertyAccessor.getProperty("misImageUrl") + citypinyin + "/images/user/" + u.getHeadportrait();
                    u.setHeadportrait(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataSourceContextHolder.setDbType(DataSourceEnum.center.value());
                Date regtime = userListDao.selectRegtimeFromCenterDb(uid);
                u.setRegtime(regtime);
            }
        }
        return u;
    }

}
