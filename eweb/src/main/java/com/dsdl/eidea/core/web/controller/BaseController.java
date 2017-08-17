package com.dsdl.eidea.core.web.controller;

import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.util.Page;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/1/13 10:21.
 * 所有Controller基类
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

    public String getMessage(String key) {
        return getUserResource().getMessage(key);
    }

    public String getMessage(String key, Object... value) {
        return getUserResource().getMessage(key, value);
    }

    public String getLabel(String key) {
        return getUserResource().getLabel(key);
    }

    public String getLabel(String key, Object... value) {
        return getUserResource().getMessage(key, value);
    }

    private UserResource getUserResource() {
        return (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
    }

    protected Page pager = null;
    public Page getPager() {
        return pager;
    }
    public void setPager(Page pager) {
        this.pager = pager;
    }
    Gson gson = new Gson();
    /**
     * @param oList 构成Grid面板JSON的List数据对象
     * @return void
     * @author yinbo.luo
     * easyui json 格式
     */
    public String getArrayTojsonForEasyUi(List<?> oList) {
        String jsonString = "{\"start\":" + pager.getStart() + ",\"page\":" + pager.getPage() + ",\"limit\":" + pager.getLimit() + ",\"total\":" + pager.getTotalCount();
        if (null != oList && oList.size() > 0) {
            String jsonStr = "";
            try {
                jsonStr = gson.toJson(oList).toString();
            } catch (Exception e) {
                System.out.println("Gson格式转换异常！");
            }
            jsonString += ",\"rows\":" + jsonStr + "}";

        } else {
            jsonString += ",\"rows\":[]}";
        }
        return jsonString;
    }
}
