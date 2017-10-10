package cn.cityre.mis.city.service;

import cn.cityre.mis.RootConfig;
import cn.cityre.mis.city.model.District;
import cn.cityre.mis.core.dao.db.CityDataSourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/24 11:13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class DistrictServiceTest {
    @Autowired(required = false)
    private DistrictService districtService;
    @Test
    public void testChangeDb()
    {
        CityDataSourceUtil.changeDB("qd");
        List<District> qdDistrictList=districtService.getAllDistrict();
        for(District district:qdDistrictList)
        {
                System.out.println("-------->"+district.getDistName());
        }
        CityDataSourceUtil.changeDB("bj");
        List<District> bjDistrictList=districtService.getAllDistrict();
        for(District district:bjDistrictList)
        {
            System.out.println("-------->"+district.getDistName());
        }
    }
}
