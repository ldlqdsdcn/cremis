package cn.cityre.mis.util;

import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.vo.UserSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 刘大磊 on 2017/9/6 15:16.
 */
public class WebUtil {
    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        return getUserSession(request.getSession());
    }

    public static UserSession getUserSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getUserSession(request);
    }

    public static String getUnionUid() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getUnionUid(request);
    }

    /**
     * 获取unionUid
     *
     * @param request
     * @return
     */
    public static String getUnionUid(HttpServletRequest request) {
        UserSession userSession = getUserSession(request.getSession());
        return userSession.getUnionUid();
    }

    /**
     * 获取unionUid
     *
     * @param session
     * @return
     */
    public static String getUnionUid(HttpSession session) {
        UserSession userSession = getUserSession(session);
        return userSession.getUnionUid();
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    public static UserSession getUserSession(HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute(WebConstant.USER_IN_SESSION);
        return userSession;
    }

    /**
     * 判断是否有错误产生如果有返回 错误验证
     * 如果没有返回null
     *
     * @param bindingResult
     * @return 如果发生错误，返回错误验证提示信息 否则返回空
     */
    public static JsonResult hasErrorMessage(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(e -> stringBuilder.append(e.getDefaultMessage()).append("<br>"));
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), stringBuilder.toString());
        }
        return null;
    }
}
