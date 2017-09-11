package cn.cityre.mis.util;

import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.sys.entity.vo.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 刘大磊 on 2017/9/6 15:16.
 */
public class WebUtil {
    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public static UserSession getUserSession(HttpServletRequest request)
    {
        return getUserSession(request.getSession());
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    public static UserSession getUserSession(HttpSession session)
    {
        UserSession userSession=(UserSession)session.getAttribute(WebConstant.USER_IN_SESSION);
        return userSession;
    }
}
