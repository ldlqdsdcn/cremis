package cn.cityre.mis.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.ifmanager.dao.MisPhoneMapper;
import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
import cn.cityre.mis.ifmanager.service.MisPhoneService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/19.
 */
@Service
public class MisPhoneServiceImpl implements MisPhoneService {
    @Autowired
    private MisPhoneMapper misPhoneMapper;
    @DataAccess(entity = MisUserPhonePo.class)
    private CommonDao<MisUserPhonePo, Integer> misUserPhoneDao;

    @Override
    public PaginationResult<MisUserPhonePo> getUserPhoneListByMyBatis(List<SearchField> searchFields, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(), queryParams.getFirstResult(), queryParams.getPageNo());
        Map<String,Object>map = new HashMap<>();
        map.put("pagingCriteria",pagingCriteria);
        if (searchFields==null){
            map.put("unionUid",null);
        }else{
            for (SearchField searchField:searchFields)
                if (searchField.getField().equals("unionUid")){
                    map.put("unionUid",searchField.getValue());
                }
        }
        PageMyBatis<MisUserPhonePo> pageMyBatis = misPhoneMapper.selectByPage(map);
        PaginationResult<MisUserPhonePo> paginationResult = PaginationResult.pagination(pageMyBatis, (int) pageMyBatis.getTotal(), queryParams.getPageNo(), queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public PaginationResult<MisUserPhonePo> getUserPhoneList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisUserPhonePo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<MisUserPhonePo> searchResult = misUserPhoneDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<MisUserPhonePo> list = misUserPhoneDao.search(search);
            paginationResult = PaginationResult.pagination(list, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public List<MisUserPhonePo> getExistPhoneByUid(String unionUid) {

        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPhonePo> misUserPhonePos = misPhoneMapper.selectByUid(unionUid);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPhonePos;
    }

    @Override
    public MisUserPhonePo getExistPhoneById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserPhonePo misUserPhonePo = misPhoneMapper.selectById(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPhonePo;
    }

    @Override
    public void updatePhoneByUid(MisUserPhonePo misUserPhonePo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhoneMapper.updateByUid(misUserPhonePo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public List<MisUserPhonePo> getExistPhoneList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPhonePo> misUserPhonePos = misPhoneMapper.selectPhoneList();
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPhonePos;
    }

    @Override
    public void createPhone(MisUserPhonePo misUserPhonePo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhoneMapper.createPhone(misUserPhonePo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void updateById(MisUserPhonePo misUserPhonePo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhoneMapper.updateById(misUserPhonePo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void deleteById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i = 0; i < ids.length; i++) {
            misPhoneMapper.deleteById(ids[i]);
        }
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    /**
     * @param misUserPhonePo
     * @return 新建时，同一个uid可以有多个phone但是primary只能有一个，验证该uid下有没有isprimary=1的phone，
     * 更新时如过，有判断两个id是否一样，一样的话，可以，不一样的话报错
     */
    @Override
    public boolean getExistPrimaryPhoneByUid(MisUserPhonePo misUserPhonePo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPhonePo> list = misPhoneMapper.selectByUid(misUserPhonePo.getUnionUid());
        Boolean isExist = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsPrimary() == 1) {
                if (misUserPhonePo.getId() == null) {
                    isExist = false;
                    break;
                } else {
                    if (list.get(i).getId() == misUserPhonePo.getId()) {
                        isExist = false;
                        break;
                    } else {
                        isExist = true;
                        break;
                    }
                }
            } else {
                isExist = false;
            }
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return isExist;
    }
}
