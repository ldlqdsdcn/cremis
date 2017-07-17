package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisApiKeyPo;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by cityre on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class ApiKeyServiceTest {
    @Autowired
    private ApiKeyService apiKeyService;
    @Test
    public void getApiKeyList() throws Exception {
        List<MisApiKeyPo> list =apiKeyService.getApiKeyList(new Search(),new QueryParams()).getData();
        for (MisApiKeyPo misApiKeyPo : list) {
            System.out.println(misApiKeyPo.getApiKey());
        }
    }

    @Test
    public void saveApiKey() throws Exception {
    }

    @Test
    public void deleteApiKey() throws Exception {
        Integer[] ids = new Integer[1];
        ids[0]=5;
        apiKeyService.deleteApiKey(ids);
        List<MisApiKeyPo> list =apiKeyService.getApiKeyList(new Search(),new QueryParams()).getData();
        for (MisApiKeyPo misApiKeyPo : list) {
            System.out.println(misApiKeyPo.getApiKey());
        }
    }

    @Test
    public void getApiKey() throws Exception {
    }

}