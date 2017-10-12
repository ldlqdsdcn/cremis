package cn.cityre.mis.core.web.security;

import cn.cityre.mis.center.model.City;
import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.sys.entity.vo.ErrorResponseVo;
import cn.cityre.mis.sys.entity.vo.LoginResponseVo;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.service.UserCitySelectedService;
import cn.cityre.mis.sys.service.UserService;
import cn.cityre.mis.util.Md5SaltTool;
import cn.cityre.mis.util.RestHelper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 刘大磊 on 2017/8/22 10:54.
 */
public class MisRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(MisRealm.class);
    private final Gson gson = new Gson();
    @Autowired
    private UserCitySelectedService userCitySelectedService;
    @Autowired
    private RestHelper restHelper;
    @Value("${admin.account.name}")
    private String adminAccount;
    @Value("${admin.account.pwd}")
    private String adminPassword;
    @Autowired
    private UserService userService;
    @Value("${centeraccount.host}")
    private String accountCenterHost;
    @Value("${centeraccount.api.login}")
    private String loginApiUrl;
    @Value("${centeraccount.api.value}")
    private String apiKey;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String unionUid = principalCollection.getRealmNames().stream().findFirst().get();
        Set<String> permissions = userService.getUserPrivileges(unionUid);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        WebDelegatingSubject subject = (WebDelegatingSubject) SecurityUtils.getSubject();
        HttpServletRequest request = (HttpServletRequest) subject.getServletRequest();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String pwd = new String(usernamePasswordToken.getPassword());
        //首先判断是否为超级管理员
        if (authenticationToken.getPrincipal().equals(adminAccount) && Md5SaltTool.validPassword(pwd, adminPassword)) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), adminAccount);

            UserSession userSession = new UserSession();
            userSession.setUserId(WebConstant.ADMINISTRATOR);
            userSession.setName(WebConstant.ADMINISTRATOR);
            userSession.setUnionUid(WebConstant.ADMINISTRATOR);
            City userCitySelected = userCitySelectedService.getUserCitySelected(WebConstant.ADMINISTRATOR);
            userSession.setCurrentProvinceCode(userCitySelected.getProvinceCode());
            userSession.setCurrentCityCode(userCitySelected.getCityCode());
            userSession.setCityName(userCitySelected.getCityName());
            initUserInfo(request, userSession);
            return authenticationInfo;
        }
        Map<String, Object> loginParam = new HashMap<>();
        loginParam.put("uid", usernamePasswordToken.getUsername());
        loginParam.put("pwd", pwd);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        RestHelper.ResponseEntity responseEntity = restHelper.get(accountCenterHost + loginApiUrl, loginParam);

        if (responseEntity.getStatus() == RestHelper.HttpStatus.OK) {
            LoginResponseVo loginResponseVo = gson.fromJson(responseEntity.getBody(), LoginResponseVo.class);
            UserSession userSession = new UserSession();
            userSession.setUnionUid(loginResponseVo.getUnionUid());
            userSession.setName(loginResponseVo.getNickName());
            userSession.setToken(loginResponseVo.getUserToken());
            userSession.setUserId(loginResponseVo.getUserId());
            City userCitySelected = userCitySelectedService.getUserCitySelected(loginResponseVo.getUnionUid());
            userSession.setCurrentProvinceCode(userCitySelected.getProvinceCode());
            userSession.setCurrentCityCode(userCitySelected.getCityCode());
            userSession.setCityName(userCitySelected.getCityName());
            initUserInfo(request, userSession);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), loginResponseVo.getUnionUid());
            return authenticationInfo;
        } else {
            ErrorResponseVo errorResponseVo = gson.fromJson(responseEntity.getBody(), ErrorResponseVo.class);
            throw new AccountException(errorResponseVo.getError().getDetail());
        }
        // throw new AccountException("用户名或密码错误");

    }

    private void initUserInfo(HttpServletRequest request, UserSession userSession) {
        request.getSession().setAttribute(WebConstant.USER_IN_SESSION, userSession);
    }


}
