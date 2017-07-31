package cn.cityre.mis.city.dao.test;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.mis.city.dao.CityAreaDao;
import cn.cityre.mis.city.entity.po.CityAreaPo;
import com.dsdl.eidea.util.BeanHelper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 刘大磊 on 2017/7/7 12:07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cremis-cfg.xml")
public class CityAreaDaoTest {
    @Autowired
    private CityAreaDao cityAreaDao;
    @Before
    public void setUp()
    {

        CityPo cityPo=new CityPo();
        cityPo.setCityid("370200");
        MockitoAnnotations.initMocks(this);
        HttpSession session=mock(HttpSession.class);
        when(session.getAttribute("currentCity")).thenReturn(cityPo);
        BeanHelper.setProperty(cityAreaDao,"session",session);
    }
    @Test
    public void testGetCityListByCurrentCity() throws NoSuchFieldException {
        List<CityAreaPo> cityAreaPoList=cityAreaDao.getCityAreaList();
        Gson gson=new Gson();
        System.out.println(gson.toJson(cityAreaPoList));
    }
}
