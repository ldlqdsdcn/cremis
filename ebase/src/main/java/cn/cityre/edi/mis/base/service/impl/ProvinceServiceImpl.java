/**
 * 版权所有 刘大磊 2013-07-01
 * 作者：刘大磊
 * 电话：13336390671
 * email:ldlqdsd@126.com
 */
package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.dao.CityDao;
import cn.cityre.edi.mis.base.dao.ProvinceDao;
import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.edi.mis.sys.entity.po.RoleCityAccessPo;
import cn.cityre.edi.mis.sys.entity.po.UserCityAccessPo;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.base.service.UserService;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cityre.edi.mis.base.entity.cpo.ProvincePo;
import cn.cityre.edi.mis.base.service.ProvinceService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Search;
import com.dsdl.eidea.core.dao.CommonDao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 刘大磊 2017-06-28 15:46:13
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private UserService userService;
    @DataAccess(entity = UserCityAccessPo.class)
    private CommonDao<UserCityAccessPo, Integer> userCityAccessDao;

    @DataAccess(entity = RoleCityAccessPo.class)
    private CommonDao<RoleCityAccessPo, Integer> roleCityAccessDao;
    @Autowired
    private CityDao cityDao;

    public PaginationResult<ProvincePo> getProvinceListByPaging(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_core");
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<ProvincePo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<ProvincePo> searchResult = provinceDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<ProvincePo> provincePoList = provinceDao.search(search);
            paginationResult = PaginationResult.pagination(provincePoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;
    }

    public ProvincePo getProvince(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return provinceDao.find(id);
    }

    public void saveProvince(ProvincePo province) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        provinceDao.save(province);
    }

    public void deletes(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        provinceDao.removeByIds(ids);
    }

    @Override
    public List<ProvincePo> getProvinceList(Integer userId) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<CityPo> cityPoList = getCityListByProvinceId(userId, null);
        Set<String> provinceIdSet = cityPoList.stream().map(e -> e.getProvinceid()).collect(Collectors.toSet());
        Search search = new Search();
        search.addFilterIn("provinceid", provinceIdSet);
        List<ProvincePo> provincePoList = provinceDao.search(search);
        DataSourceContextHolder.setDbType("dataSource_core");
        return provincePoList;
    }

    @Override
    public List<CityPo> getCityListByProvinceId(Integer userId, Integer provinceId) {
        DataSourceContextHolder.setDbType("dataSource_core");
        UserBo userBo = userService.getUser(userId);
        Integer[] roleIds = userBo.getRoleIds();
        Search roleAccessCitySearch = new Search();
        roleAccessCitySearch.addFilterIn("roleId", roleIds);
        List<RoleCityAccessPo> roleCityAccessPoList = roleCityAccessDao.search(roleAccessCitySearch);
        Set<Integer> cityIdSet = roleCityAccessPoList.stream().map(p -> p.getCityId()).collect(Collectors.toSet());
        Search userCitySearch = new Search();
        userCitySearch.addFilterEqual("userId", userId);
        List<UserCityAccessPo> userCityAccessPoList = userCityAccessDao.search(userCitySearch);
        Set<Integer> cityIdForUser = userCityAccessPoList.stream().map(p -> p.getCityId()).collect(Collectors.toSet());
        cityIdSet.addAll(cityIdForUser);
        DataSourceContextHolder.setDbType(DataSourceEnum.center.value());
        Search citySearch = new Search();
        citySearch.addFilterIn("id", cityIdSet);
        if (provinceId != null) {
            ProvincePo provincePo = provinceDao.find(provinceId);
            citySearch.addFilterEqual("provinceid", provincePo.getProvinceid());
        }

        List<CityPo> cityPoList = cityDao.search(citySearch);
        return cityPoList;
    }
}
