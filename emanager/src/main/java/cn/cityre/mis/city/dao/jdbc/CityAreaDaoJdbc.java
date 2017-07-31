package cn.cityre.mis.city.dao.jdbc;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.mis.city.dao.CityAreaDao;
import cn.cityre.mis.city.entity.po.CityAreaPo;
import com.dsdl.eidea.base.service.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired(required = false)
    private HttpSession session;

    private CityPo cityPo;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

            try
            {
                cityPo =(CityPo)session.getAttribute("currentCity");
                if(cityPo!=null)
                {
                    dataSource=SpringContextHolder.getBean(cityPo.getCityid()+"DataSource",DataSource.class);
                }
            }
            catch (Exception e)
            {

            }



        return bean;
    }

    @Override
    public List<CityAreaPo> getCityAreaList() {
        List<CityAreaPo> cityAreaPoList=new ArrayList<>();
        cityPo =(CityPo)session.getAttribute("currentCity");
        try {
            Connection connection=JdbcConnection.getConnection(cityPo.getCityid());
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select id,cityid,areaid,area,isactive from base_areas");
            while (rs.next())
            {
                int id=rs.getInt("id");
                String areaid=rs.getString("areaid");
                String cityid=rs.getString("cityid");
                String area=rs.getString("area");
                String isactive=rs.getString("isactive");
                CityAreaPo cityAreaPo=new CityAreaPo();
                cityAreaPo.setId(id);
                cityAreaPo.setArea(area);
                cityAreaPo.setAreaid(areaid);
                cityAreaPo.setCityid(cityid);
                cityAreaPo.setIsactive(isactive);
                cityAreaPoList.add(cityAreaPo);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityAreaPoList;
    }
}
