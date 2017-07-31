package cn.cityre.edi.mis.base.dao.hibernate;

import cn.cityre.edi.mis.base.dao.ProvinceDao;
import cn.cityre.edi.mis.base.entity.cpo.ProvincePo;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dao.hibernate.BaseDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 刘大磊 on 2017/7/31 15:10.
 */
@Repository
public class ProvinceDaoHibernate extends BaseDaoHibernate<ProvincePo, Integer> implements ProvinceDao {
    @Resource(name = "cityreCenterSessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
