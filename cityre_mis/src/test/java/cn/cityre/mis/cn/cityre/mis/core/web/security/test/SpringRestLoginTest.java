package cn.cityre.mis.cn.cityre.mis.core.web.security.test;

import cn.cityre.mis.util.RestHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘大磊 on 2017/8/31 10:26.
 */
public class SpringRestLoginTest {

    public static void main(String[] args)
    {
        String loginUrl="https://api-test.crevalue.cn/sup/v2/user/login";
        Map loginParam = new HashMap<>();
        loginParam.put("uid", "test002");
        loginParam.put("pwd", "123456");

        RestHelper restHelper =new RestHelper();
        restHelper.addHeader("x-api-key","45a90431c8b992e92ff3873073b47132");
        System.out.println("普通http请求");
        RestHelper.ResponseEntity re= restHelper.get(loginUrl,loginParam);
        System.out.println("spring rest http");
        System.out.println(re.getBody());

        String regUrl="http://api.crevalue.cn/sup/v2/user/register";
        Map regParam = new HashMap<>();
        regParam.put("email","liudalei@cityre.cn");
        regParam.put("password","123456");
        regParam.put("notifyUrl","www.baidu.com");
        regParam.put("phone","13336390671");
        regParam.put("phonePinToken","1234");
        RestHelper.ResponseEntity re2= restHelper.post(regUrl,regParam);
        System.out.println(re2.getBody());
    }
}
