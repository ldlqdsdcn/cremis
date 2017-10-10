package cn.cityre.mis.core.web.config;

import cn.cityre.mis.core.web.security.MisRealm;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * Created by 刘大磊 on 2017/8/21 16:15.
 * spring mvc 配置入口
 */
@Configuration
@ComponentScan({"cn.cityre.mis.**.controller", "cn.cityre.mis.**.interceptor"})
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 设置viewResolver
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        //jsp视图解析器
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(0);
    }

    /**
     * 配置静态资源的处理
     * 将请求交由Servlet处理,不经过DispatchServlet
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * shiro Realm
     *
     * @return
     */
    @Bean
    public Realm getRealm() {
        return new MisRealm();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(Realm realm, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //hiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/common/frameLogin");
        //权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/noprivileges");
        //无需授权就可以访问的页面
        shiroFilterFactoryBean.setFilterChainDefinitions(" /js/**=anon\n" +
                "/iconfont/**=anon\n" +
                "/common/frameLogin=anon\n" +
                "/common/frameLogin.jsp=anon\n" +
                "/css/**=anon\n" +
                "/image/**=anon\n" +
                "/login.jsp=anon\n" +
                "/login=anon\n" +
                "/languages=anon\n" +
                "/common/addCookie=anon\n" +
                "/common/changeLanguage=anon\n" +
                "/doLogin=anon\n" +
                "/common/frameLogin.jsp=anon\n" +
                "/error/**=anon\n" +
                "/**=authc");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }

    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.put("org.apache.shiro.authz.UnauthorizedException", "error/noprivileges");
        properties.put("org.apache.shiro.authz.UnauthenticatedException", "error/noprivileges");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }

    /**
     * 设定返回json格式数据
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        builder.createXmlMapper(true);
        XmlMapper xmlMapper = builder.createXmlMapper(true).build();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlMapper));
    }

    /**
     * 记住密码设置
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        //默认记住2个月（单位：秒）
        simpleCookie.setMaxAge(60 * 60 * 24 * 30 * 2);
        return simpleCookie;
    }

    /*
    <!-- rememberMe管理器 -->
       <bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
           <property name="cipherKey" value="#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}" />
           <property name="cookie" ref="rememberMeCookie" />
       </bean>*/
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("'4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /*
    * <!-- 基于Form表单的身份验证过滤器 -->
    * */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setLoginUrl("/login");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
