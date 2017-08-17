package cn.cityre.mis.city.merchant.service.impl;


import cn.cityre.edi.mis.base.util.CityDataSourceUtil;
import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.PropertyAccessor;
import cn.cityre.mis.city.merchant.dao.UserListDao;
import cn.cityre.mis.city.merchant.entity.po.CoInfo;
import cn.cityre.mis.city.merchant.entity.po.ServiceHa;
import cn.cityre.mis.city.merchant.entity.po.UserList;
import cn.cityre.mis.city.merchant.service.UserlistService;
import com.dsdl.eidea.util.LikeUtil;
import com.dsdl.eidea.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "userlistService")
public class UserlistServiceImpl implements UserlistService {
    @Autowired
    private UserListDao userListDao;

    /**
     * 注册账号管理
     * @param citycode
     * @param uid
     * @param phone
     * @param email
     * @param company
     * @param pager
     * @return
     */
    public List<UserList> userlist(String citycode, String uid, String phone, String email, String company, Page pager){
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        List<UserList> list = new ArrayList<UserList>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid", LikeUtil.getLike(uid));
        map.put("phone", LikeUtil.getLike(phone));
        map.put("email", LikeUtil.getLike(email));
        map.put("company", LikeUtil.getLike(company));
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        Integer count =userListDao.selectUserlistCount(map);
        pager.setTotalCount(count);
        if(count >0){
            list = userListDao.selectUserlist(map);
            for(UserList u :list){
                if(u.getCoCode() != null){
                    String url = "http://"+citycode+".cityhouse.cn/shop/"+u.getCoCode()+".html";
                    u.setCoUrl(url);
                   /* if(u.getAdid() != null){
                        Map<String,Object> haMap = new HashMap<String,Object>();
                        haMap.put("cocode",u.getCoCode());
                        haMap.put("uid",u.getUid());
                        List<ServiceHa> nowHa =userListDao.selectServiceingHa(haMap);
                        u.setServiceingHa(nowHa);
                        List<ServiceHa> outDateHa =userListDao.selectOutDateHa(haMap);
                        u.setExpiredServiceHa(outDateHa);
                    }*/
                }
            }
        }
        return list;
    }

    /**
     * 公司信息
     * @param cocode
     * @return
     */
    public CoInfo getCoInfo(String cocode, String citycode)throws Exception{
        CoInfo c = userListDao.selectCoInfo(cocode);
        if(c != null){
            int cnt =userListDao.selectCoHouseSourceCnt(cocode);
            c.setSourceCnt(cnt);
            if(c.getCoimagefile() != null && !c.getCoimagefile().equals("")){
                String url = PropertyAccessor.getProperty("misImageUrl")+citycode+"/images/co/"+c.getCoimagefile();
                c.setCoimagefile(url);
            }
        }
        return c;
    }

    /**
     * 公司下员工信息列表
     * @param cocode
     * @param pager
     * @return
     */
    public List<UserList> userinfo(String cocode, String citycode, Page pager){
        List<UserList> list = new ArrayList<UserList>();
        Integer count =userListDao.selectUserinfoCount(cocode);
        pager.setTotalCount(count);
        if(count >0){
            list = userListDao.selectUserinfo(cocode);
            for(UserList u:list){
                if(u.getHeadportrait() != null && !u.getHeadportrait().equals("")){
                    String url = "";
                    try {
                        url = PropertyAccessor.getProperty("misImageUrl") + citycode + "/images/user/" + u.getHeadportrait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    u.setHeadportrait(url);
                }
            }
        }
        return list;
    }

    public UserList serviceHaDetail(Map<String,Object> map){
        UserList u = new UserList();
        List<ServiceHa> nowHa =userListDao.selectServiceingHa(map);
        u.setServiceingHa(nowHa);
        List<ServiceHa> outDateHa =userListDao.selectOutDateHa(map);
        u.setExpiredServiceHa(outDateHa);
        return u;
    }
}
