package cn.cityre.mis.city.dao.jdbc;

import cn.cityre.edi.mis.base.entity.po.CityPo;
import cn.cityre.mis.city.dao.CityAreaDao;
import com.dsdl.eidea.base.service.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Created by 刘大磊 on 2017/7/6 9:45.
 */
@Repository
@Scope("prototype")
public class CityAreaDaoJdbc implements CityAreaDao, BeanPostProcessor {
    /**
     * 数据源
     */
    private DataSource dataSource;
    @Autowired
    private HttpSession session;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        if(cityPo!=null)
        {
            SpringContextHolder.getBean(cityPo.getCityid()+"DataSource",DataSource.class);
        }
        return bean;
    }
}
