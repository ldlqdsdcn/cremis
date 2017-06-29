package cn.cityre.edi.mis.sys.test;

import cn.cityre.edi.mis.base.entity.po.ProvincePo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 10:01.
 */
public class ProvinceServiceTest {
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
}
