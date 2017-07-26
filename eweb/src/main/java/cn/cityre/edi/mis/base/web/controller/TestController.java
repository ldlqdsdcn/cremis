package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.mis.account.service.TestService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cityre on 2017/7/10.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public String showList() {
        String result =testService.select();
        return result;
    }
}
