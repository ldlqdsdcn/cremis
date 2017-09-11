package cn.cityre.mis.common.web.controller;

import cn.cityre.mis.common.exception.AccountException;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 刘大磊 on 2017/8/22 11:21.
 * 登录页
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String username, String password, boolean rememberMe) {
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (rememberMe) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "用户名或密码错误");
            return modelAndView;
        } catch (AccountException exception) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", exception.getMessage());
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
