package cn.cityre.mis.mail.test;

import cn.cityre.mis.RootConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 刘大磊 on 2017/8/23 16:29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class MailSendTest {

    @Autowired
    private JavaMailSender mailSender;
    @Test
    public void testSendMail()
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("swms_admin@126.com");
            message.setTo("ldlqdsd@126.com");
            message.setSubject("主题：简单邮件");
            message.setText("测试邮件内容");
            mailSender.send(message);
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}
