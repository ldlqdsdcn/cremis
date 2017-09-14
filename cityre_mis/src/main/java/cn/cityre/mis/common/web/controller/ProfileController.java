package cn.cityre.mis.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 刘大磊 on 2017/9/14 14:15.
 */
@RequestMapping("/profile")
@Controller
public class ProfileController {
    @RequestMapping("/showChangePassword")
    public String showChangePassword() {
        return "changePassword";
    }

}
