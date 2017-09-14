package cn.cityre.mis.center.service.test;

import cn.cityre.mis.RootConfig;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.util.RestHelper;
import cn.cityre.mis.util.WebUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘大磊 on 2017/9/14 15:41.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class ChangePasswordTest {
    @Value("${centeraccount.host}")
    private String accountCenterHost;
    @Value("${centeraccount.api.login}")
    private String changePasswordUrl;
    @Value("${centeraccount.api.changepassword}")
    private String apiKey;
    @Autowired
    private RestHelper restHelper;

    public void testChangePassword() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        headers.add("token", WebUtil.getUserSession().getToken());

        Map<String, Object> param = new HashMap<>();
        param.put("password", "888888");
        param.put("oldPassword", "123456");
        RestHelper.ResponseEntity responseEntity = restHelper.get(accountCenterHost + changePasswordUrl, param);
        Assert.assertTrue(responseEntity.getStatus() == RestHelper.HttpStatus.OK);
    }
}
