package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.DicPostTypeMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.DicPostType;
import cn.cityre.mis.cityreaccount.datavip.service.DicPostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/8/16.
 */
@Service(value = "dicPostTypeService")
public class DicPostTypeServiceImpl implements DicPostTypeService {
    @Autowired
    private DicPostTypeMapper dicPostTypeMapper;
    @Override
    public List<DicPostType> getExistPostTypeList() {
        List<DicPostType> list = dicPostTypeMapper.selectAllPostType();

        return list;
    }
}
