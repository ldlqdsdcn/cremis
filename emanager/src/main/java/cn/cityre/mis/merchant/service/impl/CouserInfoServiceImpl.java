package cn.cityre.mis.merchant.service.impl;


import cn.cityre.mis.merchant.dao.CoCheckDao;
import cn.cityre.mis.merchant.dao.CoUserCheckDao;
import cn.cityre.mis.merchant.dao.CouserInfoDao;
import cn.cityre.mis.merchant.dao.UserListDao;
import cn.cityre.mis.merchant.entity.po.CoCheck;
import cn.cityre.mis.merchant.entity.po.CoUser;
import cn.cityre.mis.merchant.entity.po.CouserInfo;
import cn.cityre.mis.merchant.entity.po.Perdaycount;
import cn.cityre.mis.merchant.service.CouserInfoService;
import com.dsdl.eidea.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "couserInfoService")
public class CouserInfoServiceImpl implements CouserInfoService {
    @Autowired
    private CouserInfoDao couserInfoDao;
    @Autowired
    private CoCheckDao coCheckDao;
    @Autowired
    private UserListDao userListDao;
    @Autowired
    private CoUserCheckDao coUserListDao;
    @Override
    public List<CouserInfo> couserInfo(Map<String, Object> map, Page pager) {
        List<CouserInfo> list = new ArrayList<CouserInfo>();
        int count = couserInfoDao.selectCouserInfoCount(map);
        pager.setTotalCount(count);
        if(count >0){
            list =couserInfoDao.selectCouserInfoList(map);
            for(CouserInfo c: list){
                if(c.getIsadmin() != null && c.getIsadmin() == 1){
                    c.setAdminstr("*");
                }else if(c.getUser_type() != null && !c.getUser_type().equals("")) {
                    CoCheck co = coCheckDao.selectCoByCocode(c.getUser_type());
                    if(co != null ){
                        if(co.getCostate() == null || co.getCostate().equals("")){
                            c.setAdminstr("公司待审核");
                        }
                    }
                }
                    if(c.getConame() == null || c.getConame().equals("")){
                        c.setAddress(c.getUsercoaddr());
                    }else{
                        c.setAddress(c.getCoaddr());
                    }

                List<String> telList = couserInfoDao.selectTelByUid(c.getUid());
                if(telList != null && telList.size()>0){
                        String tel = "";
                        for(int i=0;i<telList.size();i++){
                            tel = tel +telList.get(i)+",";
                        }
                        tel = tel.substring(0,tel.length()-1);
                        c.setTel(tel);
                }
                List<String> emailList =couserInfoDao.selectEmailByUid(c.getUid());
                if(emailList != null && emailList.size()>0){
                        String email = "";
                        for(int i=0;i<emailList.size();i++){
                            email = email +emailList.get(i)+",";
                        }
                        email= email.substring(0,email.length()-1);
                        c.setEmail(email);
                }
                int num = couserInfoDao.selectAdCountByUid(c.getUid());
                if(num >0){
                    c.setAdapplay("*");
                }
            }
        }
        return list;
    }
    public List<Perdaycount> perdaycount(String uid){
        List<Perdaycount> list=new ArrayList<Perdaycount>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        //cd.add(Calendar.MONTH, -1);
        cd.set(Calendar.DATE, 1);
        Date now = cd.getTime();
        map.put("nowDate",sdf.format(now));
        list =couserInfoDao.selectPerdaycount(map);
        return list;
    }

    public String userlist_agree(String uid,String sysuid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",sysuid);
        map.put("userlistuid",uid);
        userListDao.updateUserlist_flag_uid(map);
        return "OK";
    }

    public String userlist_refuse(String uid,String sysuid,String reason){
        CoUser couser =coUserListDao.selectCoUserByUid(uid);

        if(couser != null ){
            if(couser.getIsadmin() != null ){
                if(couser.getIsadmin() == 1){
                    Map<String,Object> mapco = new HashMap<String,Object>();
                    mapco.put("sysuid",sysuid);
                    mapco.put("cocode",couser.getCocode());
                    couserInfoDao.updateCoByCocode(mapco);
                }
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        map.put("sysuid",sysuid);
        map.put("reason",reason);
        couserInfoDao.updateUserlist_remark_uid(map);
        //把该用户房源置过期
        //出售
        String dealcode1 = couserInfoDao.selectDealcodeFromQd_forsale(uid);
        if(dealcode1 != null){
            Map<String,Object> map10 = new HashMap<String,Object>();
            map.put("type",10);
            map.put("dealcode",dealcode1);
            coCheckDao.selectPro_fl_history(map10);
        }
        couserInfoDao.updateQd_forsale_validDate(uid);

        //出租
        String dealcode2 = couserInfoDao.selectDealcodeFromQd_lease(uid);
        if(dealcode1 != null){
            Map<String,Object> map20 = new HashMap<String,Object>();
            map.put("type",20);
            map.put("dealcode",dealcode2);
            coCheckDao.selectPro_fl_history(map20);
        }
        couserInfoDao.updateQd_lease_validDate(uid);


        return "OK";
    }

    public String changetoreguser(String uid){
        String result = "";
        int qd_forsale_cnt = couserInfoDao.selectCount_qd_forsale(uid);
        int qd_lease_cnt = couserInfoDao.selectCount_qd_lease(uid);
        if((qd_forsale_cnt + qd_lease_cnt) >3){
            result = "该经纪人已经发布大于3条房源，请删除或处理其房源后再进行该操作！";
        }else{
            couserInfoDao.deleteFromCouser(uid);
            couserInfoDao.updateQd_forsale_source(uid);
            couserInfoDao.updateQd_lease_source(uid);
            couserInfoDao.updateUserlistChange(uid);
            result = "已经把该经纪人转为个人！";
        }
        return result;
    }
}
