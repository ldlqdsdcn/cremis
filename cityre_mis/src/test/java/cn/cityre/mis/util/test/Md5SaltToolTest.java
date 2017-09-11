package cn.cityre.mis.util.test;

import cn.cityre.mis.util.Md5SaltTool;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 刘大磊 on 2017/8/24 16:18.
 */
public class Md5SaltToolTest {
    @Test
    public void testMd5Salt()
    {
        String password="88888888";
        String md5SaltStr=Md5SaltTool.getEncryptedPwd(password);
        System.out.println(md5SaltStr);
        Assert.assertTrue(Md5SaltTool.validPassword(password,md5SaltStr));
    }
}
