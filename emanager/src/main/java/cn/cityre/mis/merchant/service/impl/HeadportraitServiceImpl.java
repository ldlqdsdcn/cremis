package cn.cityre.mis.merchant.service.impl;


import cn.cityre.edi.mis.base.util.PropertyAccessor;
import cn.cityre.mis.merchant.dao.CoCheckDao;
import cn.cityre.mis.merchant.dao.HeadportraitDao;
import cn.cityre.mis.merchant.dao.UserListDao;
import cn.cityre.mis.merchant.entity.po.CoCheck;
import cn.cityre.mis.merchant.entity.po.Headportrait;
import cn.cityre.mis.merchant.entity.po.UserList;
import cn.cityre.mis.merchant.service.HeadportraitService;
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
@Service(value = "headportraitService")
public class HeadportraitServiceImpl implements HeadportraitService {
    @Autowired
    private UserListDao userListDao;
    @Autowired
    private CoCheckDao coCheckDao;
    @Autowired
    private HeadportraitDao headportraitDao;
    public List<Headportrait> headportrait(Map<String,Object> map,Page pager ){
        List<Headportrait> list = new ArrayList<Headportrait>();
        if(map.get("headType").equals("reguser")){
            Integer count = headportraitDao.selectHeadportraitCount_reguser(map);
            pager.setTotalCount(count);
            if(count >0){
                list = headportraitDao.selectHeadportraitList_reguser(map);
                for(Headportrait h:list){
                        if(h.getImagefile() != null ){
                            String url = null;
                            try {
                                url = PropertyAccessor.getProperty("misImageUrl") + map.get("city_pinyin").toString() + "/images/user/" + h.getImagefile();
                                h.setImageURL(url);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                }
            }
        }else if(map.get("headType").equals("co")){
            Integer count = headportraitDao.selectHeadportraitCount_co(map);
            pager.setTotalCount(count);
            if(count >0){
                list = headportraitDao.selectHeadportraitList_co(map);
                for(Headportrait h:list){
                    if(h.getImagefile() != null ){
                        String url = null;
                        try {
                            url = PropertyAccessor.getProperty("misImageUrl") + map.get("city_pinyin").toString() + "/images/co/" + h.getImagefile();
                            h.setImageURL(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }else if(map.get("headType").equals("couser")){
            Integer count = headportraitDao.selectHeadportraitCount_couser(map);
            pager.setTotalCount(count);
            if(count >0){
                list = headportraitDao.selectHeadportraitList_couser(map);
                for(Headportrait h:list){
                    if(h.getImagefile() != null ){
                        String url = null;
                        try {
                            url = PropertyAccessor.getProperty("misImageUrl") + map.get("city_pinyin").toString() + "/images/user/" + h.getImagefile();
                            h.setImageURL(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 单个审核
     * @param id
     * @param flag
     * @param sysuid
     * @param headtype
     * @return
     */
    public String checkone(String id, int flag, String sysuid,String headtype,String citypinyin){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",flag);
        map.put("uid",sysuid);
        map.put("id",Integer.parseInt(id));
        if(headtype.equals("reguser") || headtype.equals("couser")){
            headportraitDao.updateHeadportrait_user(map);
            if(flag == 1){
                UserList u =  userListDao.selectUserlistById(Integer.parseInt(id));
                String headportrait = u.getHeadportrait();
//                this.sendGet(headtype,headportrait,citypinyin);
            }
        }else if(headtype.equals("co")){
            headportraitDao.updateHeadportrait_co(map);
            CoCheck c = coCheckDao.selectCoById(Integer.parseInt(id));
            String coimagefile = c.getCoimagefile();
//            this.sendGet(headtype,coimagefile,citypinyin);
        }


        return "OK";
    }

    /**
     * 批量审核
     * @param idStr
     * @param certification
     * @param sysuid
     * @param headtype
     * @return
     */
    public String checkAll(String idStr,int certification,String sysuid,String headtype,String citypinyin){
        String[] array=idStr.split(",");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("certification",certification);
        map.put("uid",sysuid);
        map.put("list",array);
        if(headtype.equals("reguser") || headtype.equals("couser")){
            headportraitDao.updateHeadportraitAll_user(map);
            if(certification == 1){
                for(int i=0;i<array.length;i++){
                    UserList u = userListDao.selectUserlistById(Integer.parseInt(array[i]));
                    String headportrait = u.getHeadportrait();
//                    this.sendGet(headtype,headportrait,citypinyin);
                }

            }
        }else if(headtype.equals("co")){
            headportraitDao.updateHeadportraitAll_co(map);
            if(certification == 1){
                for(int i=0;i<array.length;i++){
                    CoCheck c = coCheckDao.selectCoById(Integer.parseInt(array[i]));
                    String coimagefile = c.getCoimagefile();
//                    this.sendGet(headtype,coimagefile,citypinyin);
                }
            }
        }
        return "OK";
    }

//    public  String sendGet(String headtype,String image,String citypinyin) {
//
//        String str1 = image.trim();
//        String str = str1.replace("'","");
//        String url="";
//        try {
//            if(headtype.equals("reguser") || headtype.equals("couser")){
//                url = PropertyAccessor.getProperty("imagethumbnailpath") + "sitetest.jsp?type=user&picname ="+str+"&citycode="+citypinyin;
//            }else if(headtype.equals("co")){
//                url = PropertyAccessor.getProperty("imagethumbnailpath") + "sitetest.jsp?type=co&picname ="+str+"&citycode="+citypinyin;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        String result = "";
//        BufferedReader in = null;
//        try {
//
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }
}
