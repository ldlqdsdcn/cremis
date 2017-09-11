package cn.cityre.mis.cityre_center.dao.test;

import cn.cityre.mis.RootConfig;
import cn.cityre.mis.cityre_center.dao.DicUserTypeMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/23 17:54.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
public class DicUserTypeMapperTest {
    @Autowired
    private DicUserTypeMapper dicUserTypeMapper;
    @Test
    public void testGetDicUserTypeList()
    {
        List list=dicUserTypeMapper.selectList(null);
        Assert.assertTrue(list.size()>0);
    }
}
