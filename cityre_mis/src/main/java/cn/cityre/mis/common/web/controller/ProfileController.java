package cn.cityre.mis.common.web.controller;

import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.def.SystemConsts;
import cn.cityre.mis.sys.entity.vo.ErrorResponseVo;
import cn.cityre.mis.util.RestHelper;
import cn.cityre.mis.util.WebUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘大磊 on 2017/9/14 14:15.
 */
@RequestMapping("/profile")
@Controller
public class ProfileController {
    @Value("${centeraccount.host}")
    private String accountCenterHost;
    @Value("${centeraccount.api.changepassword}")
    private String changePasswordUrl;
    @Value("${centeraccount.api.value}")
    private String apiKey;
    @Autowired
    private RestHelper restHelper;

    @RequestMapping("/showChangePassword")
    public String showChangePassword() {
        return "profile/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> changePassword(String oldPassword, String newPassword) {

        String unionUid = WebUtil.getUnionUid();
        if (SystemConsts.SUPER_USER.equals(unionUid)) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), "超级管理员的密码是系统初始化设置的，不允许修改");
        }

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("x-api-key", apiKey);
        headerMap.put("Authorization", " Bearer " + WebUtil.getUserSession().getToken());
        Map<String, Object> param = new HashMap<>();
        param.put("password", newPassword);
        param.put("oldPassword", oldPassword);
        RestHelper.ResponseEntity responseEntity = restHelper.post(accountCenterHost + changePasswordUrl, param, headerMap);
        if (responseEntity.getStatus() == RestHelper.HttpStatus.OK) {
            return JsonResult.success(null);
        } else {
            Gson gson = new Gson();
            ErrorResponseVo errorResponseVo = gson.fromJson(responseEntity.getBody(), ErrorResponseVo.class);
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), errorResponseVo.getError().getDetail());
        }

    }

}
