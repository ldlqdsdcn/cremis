package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.DicPayTypeMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.DicPayType;
import cn.cityre.mis.cityreaccount.datavip.service.DicPayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/8/16.
 */
@Service(value = "dicPayTypeService")
public class DicPayTypeServiceImpl implements DicPayTypeService {
    @Autowired
    private DicPayTypeMapper dicPayTypeMapper;
    @Override
    public List<DicPayType> getExistAllType() {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        List<DicPayType> list = dicPayTypeMapper.selectAllType();
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
    }
}
