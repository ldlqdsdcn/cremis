package cn.cityre.mis.datavip.service.Impl;

import cn.cityre.mis.datavip.entity.AppProduct;
import cn.cityre.mis.datavip.dao.AppProductMapper;
import cn.cityre.mis.datavip.service.AppProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/28.
 */
@Service
public class AppProductServiceImpl implements AppProductService {
    @Autowired
    private AppProductMapper appProductMapper;
    @Override
    public List<AppProduct> getAppProductList() {
        return appProductMapper.selectAllList();
    }
}
