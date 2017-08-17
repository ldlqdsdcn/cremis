package cn.cityre.edi.mis.sys.test;

import cn.cityre.edi.mis.base.entity.cpo.ProvincePo;
import cn.cityre.edi.mis.base.service.ProvinceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 10:01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ProvinceServiceTest {
    @Autowired
    private ProvinceService provinceService;
    @Test
    public void testProvinceSort()
    {
        List<ProvincePo> provincePoList=new ArrayList<>();
        ProvincePo provincePo=new ProvincePo();
        provincePo.setId(2);
        provincePo.setProvince("山东");
        provincePoList.add(provincePo);
        provincePo=new ProvincePo();
        provincePo.setId(5);
        provincePo.setProvince("广州");
        provincePoList.add(provincePo);
        provincePo=new ProvincePo();
        provincePo.setId(1);
        provincePo.setProvince("北京");
        provincePoList.add(provincePo);

        for(ProvincePo p:provincePoList)
        {
            System.out.println(p.getId()+" "+p.getProvince());
        }
        provincePoList.sort((ProvincePo h1, ProvincePo h2) -> h1.getId().compareTo(h2.getId()));
        for(ProvincePo p:provincePoList)
        {
            System.out.println(p.getId()+" "+p.getProvince());
        }
    }
    @Test
    public void testGetProvinceByUserId()
    {
        List<ProvincePo> provincePoList=provinceService.getProvinceList(1);

        for(ProvincePo provincePo:provincePoList)
        {
            System.out.println(provincePo.getProvince());
        }
    }
}
