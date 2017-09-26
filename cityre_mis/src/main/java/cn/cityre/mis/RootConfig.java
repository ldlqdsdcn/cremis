package cn.cityre.mis;

import cn.cityre.mis.core.dao.config.*;
import cn.cityre.mis.util.RestHelper;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 刘大磊 on 2017/8/22 9:02.
 */
@Configuration
@Import(value = {MisDaoConfig.class, AccountDaoConfig.class, CenterDaoConfig.class, CityrecenterDaoConfig.class, CityDaoConfig.class})
@ComponentScan({"cn.cityre.mis.**.service","cn.cityre.mis.core.dao.db"})
@EnableTransactionManagement
public class RootConfig {
    private static final Logger log = LoggerFactory.getLogger(RootConfig.class);

    @Value("${mailserver.host}")
    private String host;
    @Value("${mailserver.port}")
    private Integer port;
    @Value("${mailserver.username}")
    private String username;
    @Value("${mailserver.password}")
    private String password;
    @Value("${mailserver.default_encoding}")
    private String encoding;
    @Value("${mailserver.smtp.auth}")
    private boolean auth;
    @Value("${centeraccount.api.key}")
    private String apiKey;
    @Value("${centeraccount.api.value}")
    private String apiValue;

    /**
     * ehcache配置
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager);
        ehCacheCacheManager.setTransactionAware(true);
        return ehCacheCacheManager;
    }

    /**
     * 发送邮件
     *
     * @return
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(encoding);
        javaMailSender.getJavaMailProperties().put("mail.smtp.auth", auth);
        return javaMailSender;
    }

    @Bean
    public RestHelper httpHelper() {
        RestHelper restHelper = new RestHelper();
        restHelper.addHeader(apiKey, apiValue);
        return restHelper;
    }
}
