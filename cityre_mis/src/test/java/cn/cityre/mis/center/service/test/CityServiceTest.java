package cn.cityre.mis.center.service.test;

import cn.cityre.mis.RootConfig;
import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.service.CityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/23 17:24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class CityServiceTest {
    @Autowired(required = false)
    private CityService cityService;

    @Test
    public void testGetAllCityList() {
        List<City> allCityList = cityService.getAllCityList();
        Assert.assertTrue(allCityList.size() > 0);
    }
}
