package cn.cityre.mis.merchant.service.impl;


import cn.cityre.edi.mis.base.util.PropertyAccessor;
import cn.cityre.mis.merchant.dao.CoCheckDao;
import cn.cityre.mis.merchant.entity.po.CoCheck;
import cn.cityre.mis.merchant.service.CompanyCheckService;
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
@Service(value = "companyCheckService")
public class CompanyCheckServiceImpl implements CompanyCheckService {
    @Autowired
    private CoCheckDao coCheckDao;

    /**
     * 公司审核营业执照
     * @param status
     * @param companyName
     * @param subStartDate
     * @param subEndDate
     * @param chkStartDate
     * @param chkEndDate
     * @param pager
     * @return
     */
    public List<CoCheck> companyCheck(String citypinyin, String status, String companyName, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, Page pager){

        List<CoCheck> list = new ArrayList<CoCheck>();
        Map<String,Object> map = new HashMap<String,Object>();
        if(status != null && !status.equals("")){
            map.put("status", Integer.parseInt(status));
        }
        map.put("companyName", LikeUtil.getLike(companyName));
        map.put("subStartDate",subStartDate);
        map.put("subEndDate",subEndDate);
        map.put("chkStartDate",subEndDate);
        map.put("chkEndDate",subEndDate);
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        Integer count =coCheckDao.selectCoCheckCount(map);
        pager.setTotalCount(count);
        if(count >0){
            list = coCheckDao.selectCoCheckList(map);
            for(CoCheck c :list){
                String url = null;
                try {
                    url = PropertyAccessor.getProperty("misImageUrl")+citypinyin+"/images/co/"+c.getCoimagefile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c.setCertPhoto(url);
            }
        }
        return list;
    }
    public String checkAll(String idStr,int certification,String uid){
        String[] array=idStr.split(",");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("certification",certification);
        map.put("uid",uid);
        map.put("list",array);
        coCheckDao.updateCoCheckAll(map);
        //公司实名认证通过后，用户资料同时通过审核，如果已通过电话认证则不置状态
        if(certification == 1){
                for(int i=0;i<array.length;i++){
                    CoCheck co = coCheckDao.selectCoById(Integer.parseInt(array[i]));
                        if(co.getCostate()!=null && !co.getCostate().equals(1)){
                            map.put("cocode",co.getCoCode());
                            coCheckDao.updateCoByCoCode(map);
                            this.commonUpdate(co.getCoCode(),co.getCouid());
                            //公司用户转为经济人   asp上写的
                            coCheckDao.updateCouserByUsertype(co.getCoCode());
                            coCheckDao.updateCouserInnerCo(co.getCoCode());
                            String couid = coCheckDao.selectAdInnerCo(co.getCoCode());
                            if(couid != null ){
                                map.put("couid",couid);
                                coCheckDao.updateAdByUidAndCocode(map);
                            }
                        }
                }

        }
        return "OK";
    }
    public String checkone(String cocode, int certification, String uid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("certification",certification);
        map.put("uid",uid);
        map.put("cocode",cocode);
        coCheckDao.updateCoCheckOne(map);
        if(certification == 1){
            CoCheck co = coCheckDao.selectCoByCocode(cocode);
                if(co.getCostate()!=null && !co.getCostate().equals(1)){
                    map.put("cocode",co.getCoCode());
                    coCheckDao.updateCoByCoCode(map);
                    this.commonUpdate(co.getCoCode(),co.getCouid());
                    //公司用户转为经济人   asp上写的
                    coCheckDao.updateCouserByUsertype(co.getCoCode());
                    coCheckDao.updateCouserInnerCo(co.getCoCode());
                    String couid = coCheckDao.selectAdInnerCo(co.getCoCode());
                    if(couid != null ){
                        map.put("couid",couid);
                        coCheckDao.updateAdByUidAndCocode(map);
                    }
                }
        }
        return "OK";
    }
    public void commonUpdate(String cocode,String couid){
        if(couid != null && !couid.equals("")){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("cocode",cocode);
            map.put("couid",couid);
            //更新出售房源
            coCheckDao.updateQd_forsale(map);
            String dealcodeF = coCheckDao.selectDealcodeFromQd_forsale(couid);
            if(dealcodeF != null ){
                Map<String,Object> map1 = new HashMap<String,Object>();
                map1.put("type",10);
                map1.put("dealcode",dealcodeF);
                //调用存储过程
                coCheckDao.selectPro_fl_history(map1);
            }
            //更新出租房源
            coCheckDao.updateQd_lease(map);
            String dealcodeL = coCheckDao.selectDealcodeFromQd_lease(couid);
            if(dealcodeL != null ){
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("type",10);
                map2.put("dealcode",dealcodeF);
                //调用存储过程
                coCheckDao.selectPro_fl_history(map2);
            }
        }
    }
}
