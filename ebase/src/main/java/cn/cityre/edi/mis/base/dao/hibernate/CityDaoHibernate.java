package cn.cityre.edi.mis.base.dao.hibernate;

import cn.cityre.edi.mis.base.dao.CityDao;
import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dao.hibernate.BaseDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by 刘大磊 on 2017/7/31 15:10.
 */
@Repository
public class CityDaoHibernate extends BaseDaoHibernate<CityPo, Integer> implements CityDao {
    @Resource(name = "cityreCenterSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
