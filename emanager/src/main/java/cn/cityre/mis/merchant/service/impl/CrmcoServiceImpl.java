package cn.cityre.mis.merchant.service.impl;


import cn.cityre.mis.merchant.dao.CoCheckDao;
import cn.cityre.mis.merchant.dao.CoUserCheckDao;
import cn.cityre.mis.merchant.dao.CrmcoDao;
import cn.cityre.mis.merchant.dao.UserListDao;
import cn.cityre.mis.merchant.entity.po.*;
import cn.cityre.mis.merchant.service.CompanyCheckService;
import cn.cityre.mis.merchant.service.CrmcoService;
import com.dsdl.eidea.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "crmcoService")
public class CrmcoServiceImpl implements CrmcoService {
    @Autowired
    private CoCheckDao coCheckDao;
    @Autowired
    private CrmcoDao crmcoDao;
    @Autowired
    private CompanyCheckService companyCheckService;
    @Autowired
    private CoUserCheckDao coUserCheckDao;

    @Autowired
    private UserListDao userListDao;

    @Override
    public List<Cocl> getcocl(){
            return crmcoDao.selectCocl();
    }
    @Override
    public List<Crmco> list(Map<String,Object> map, Page pager){
        List<Crmco> list = new ArrayList<Crmco>();
        int count =crmcoDao.selectCrmcoCount(map);
        pager.setTotalCount(count);
        if(count >0){
            list =crmcoDao.selectCrmcoList(map);
            for(Crmco c:list){
                Map<String,Object> m = new HashMap<String,Object>();
                m.put("cocode",c.getCocode());
                m.put("couid",c.getCouid());
                int num = crmcoDao.selectCouserCnt(m);
                c.setCouserCnt(num);
            }
        }
        return list;
    }

    public List<Perdaycount> perdaycount(String cocode){
        List<Perdaycount> list = new ArrayList<Perdaycount>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cocode",cocode);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DATE, 1);
        Date now = cd.getTime();
        map.put("nowdate",sdf.format(now));
        list =crmcoDao.selectPerdaycount(map);
        return list;
    }

    public String crmco_agree(String cocode,String couid,String sysuid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cocode",cocode);
        map.put("sysuid",sysuid);
        crmcoDao.updateCoStateByCocode(map);

        companyCheckService.commonUpdate(cocode,couid);

        coCheckDao.updateCouserByUsertype(cocode);
        coCheckDao.updateCouserInnerCo(cocode);

      /*
        '公司通过审核时，如果该公司管理员以前是独立经纪人并且购买了小区中介尚未到期，
        '则同时修改ad表中的uid为cocode
        '提出人 gy*/
        String adcouid = coCheckDao.selectAdInnerCo(cocode);
        if(couid != null ){
            map.put("couid",adcouid);
            coCheckDao.updateAdByUidAndCocode(map);
        }
        return "OK";
    }

    public String crmco_refuse(String cocode,String reason,String sysuid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cocode",cocode);
        map.put("sysuid",sysuid);
        map.put("sysuid",reason);
        crmcoDao.updateCo_remark_cocode(map);
        return "OK";
    }

    public   Map<String,Object> crmco_edit(Map<String,Object> param){
        Map<String,Object> result = new HashMap<String,Object>();
        int num = crmcoDao.selectCoByConameCnt(param);
        if(num >0){
            result.put("msg","公司名重复!");
            result.put("flag",false);

        }else{
            param.put("updateTime",new Date());
            param.put("VerifyTime",new Date());
            crmcoDao.updateCoDetail(param);
            result.put("msg","修改成功!");
            result.put("flag",true);
        }
        return result;
    }

    public   Map<String,Object> crmco_delete(Map<String,Object> param){
        Map<String,Object> result = new HashMap<String,Object>();
        int num1 = crmcoDao.seleteHa_coByCocodeCnt(param.get("cocode").toString());
        if(num1 >0){
            result.put("msg","该公司和小区存在关联，不能删除!");
            result.put("flag",false);
            return result;
        }
        int num2 = crmcoDao.seleteAdByCocodeCnt(param.get("cocode").toString());
        if(num2 >0){
            result.put("msg","该公司存在小区中介服务，不能删除!");
            result.put("flag",false);
            return result;
        }
        CoUser less =coUserCheckDao.selectUser_typeLess2(param.get("cocode").toString());
        if(less != null){
            coUserCheckDao.updateUser_typeLess2(param.get("cocode").toString());

        }
        CoUser big =coUserCheckDao.selectUser_typeBig2(param.get("cocode").toString());
        if(big != null){
            coUserCheckDao.updateUser_typeBig2(param.get("cocode").toString());

        }
        Map<String,Object> paramco = new HashMap<String,Object>();
        paramco.put("cocode",param.get("cocode"));
        paramco.put("certification","1");
        CoCheck co = coCheckDao.selectCoByCocodeCertification(paramco);
        if(co != null){
            coUserCheckDao.updateCocodeEqualUid(param.get("cocode").toString());
        }
        coCheckDao.deleteCoByCocode(param.get("cocode").toString());
        coCheckDao.deleteCo_matchinfoByCocode(param.get("cocode").toString());
        result.put("msg","该公司信息删除成功!");
        result.put("flag",true);
        return result;
    }
    public   List<CoUser> getCoManage(Map<String,String> param){
        return coUserCheckDao.selectCouser_remanage(param);
    }

    public   Map<String,Object> crmco_remanage(Map<String,Object> param){
        Map<String,Object> result = new HashMap<String,Object>();
        String permission1 = userListDao.selectPermissionByUid(param.get("olduid").toString());
        String permission2 = userListDao.selectPermissionByUid(param.get("newuid").toString());
        String newpermission ="";
        if(permission1 != null && !permission1.equals("")){
            if(permission2 == null || permission2.equals("")){
                newpermission =permission1;
            }else{
                newpermission = permission2;
                String[] a2 = permission2.split(",");
                String[] a1 = permission1.split(",");
                for(int i=0;i<a1.length;i++){
                    boolean flag = true;
                    for(int j=0;j<a2.length;j++){
                        if(a1[i].equals(a2[j])){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        newpermission = newpermission+a1[i]+",";
                    }
                }
            }
        }else{
            if(permission2 != null && !permission1.equals("")){
                newpermission =permission2;
            }
        }
        Map<String,Object> permissionmap = new HashMap<String,Object>();
        permissionmap.put("uid",param.get("newuid").toString());

        if(newpermission.equals("")){
            permissionmap.put("permission",null);
        }else{
            permissionmap.put("permission",newpermission);
        }
        userListDao.updatePermissionByUid(permissionmap);
        coUserCheckDao.updateNewManage(param);
        coCheckDao.updateCouidByCocode(param);
        coUserCheckDao.updateOldManage(param);
        result.put("msg","操作成功，管理员已变更！");
        result.put("flag",true);
        return result;
    }
}
