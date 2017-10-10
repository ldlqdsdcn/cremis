package cn.cityre.mis.account.service.test;

import cn.cityre.mis.RootConfig;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.account.service.AccountUserService;
import com.github.pagehelper.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 刘大磊 on 2017/8/23 16:43.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountUserServiceTest {
    @Autowired(required = false)
    private AccountUserService accountUserService;
    @Test
    public void testGetAccountUserList()
    {
        Page<AccountUser> accountUserPageMyBatis = accountUserService.getAccountUser(20, 50);
        for(AccountUser accountUser:accountUserPageMyBatis)
        {
            System.out.println(accountUser.getUserid());
        }
        Assert.assertTrue(accountUserPageMyBatis.size()==50);
    }
}
