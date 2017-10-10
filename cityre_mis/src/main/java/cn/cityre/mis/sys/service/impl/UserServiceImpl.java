package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.account.dao.AccountUserMapper;
import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.center.service.CityService;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.dao.UserCityMapper;
import cn.cityre.mis.sys.entity.bo.ProvinceBo;
import cn.cityre.mis.sys.entity.bo.UserCityBo;
import cn.cityre.mis.sys.entity.query.UserCityQuery;
import cn.cityre.mis.sys.model.UserCity;
import cn.cityre.mis.sys.service.UserService;
import cn.cityre.mis.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 刘大磊 on 2017/8/23 14:36.
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${admin.account.name}")
    private String adminAccount;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private UserCityMapper userCityMapper;
    @Autowired
    private CityService cityService;

    /**
     * @param username
     * @return
     * @see UserService#getUserByUsername(String)
     */
    public AccountUser getUserByUsername(String username) {
        AccountUserQuery param = new AccountUserQuery();
        param.setUserId(username);
        List<AccountUser> accountUserList = accountUserMapper.selectList(param);
        if (accountUserList.size() > 0) {
            return accountUserList.get(0);
        }
        return null;
    }

    @Cacheable(value = "userContentCache", key = "#unionUid")
    @Transactional
    @Override
    public Set<String> getUserPrivileges(String unionUid) {
        if (StringUtil.isEmpty(unionUid)) {
            throw new IllegalArgumentException("unionUid不允许为空");
        }
        if (adminAccount.equals(unionUid)) {
            return repositoryMapper.getAllRepositories();
        }
        return repositoryMapper.getUserRepositories(unionUid);
    }

    @Override
    public UserCityBo getUserCityBo(Integer id) {
        AccountUser accountUser = accountUserMapper.selectByPrimaryKey(id);
        UserCityQuery userCityQuery = new UserCityQuery();
        userCityQuery.setUnionUid(accountUser.getUnionuid());

        List<UserCity> userCityList = userCityMapper.selectList(userCityQuery);
        List<String> userCityIdList = userCityList.stream().map(UserCity::getCityCode).collect(Collectors.toList());
        List<ProvinceBo> provinceBoList = cityService.getProvinceBoList(userCityIdList);
        UserCityBo userCityBo = new UserCityBo();
        userCityBo.setUnionUid(accountUser.getUnionuid());
        userCityBo.setName(accountUser.getUserid());
        userCityBo.setProvinceBoList(provinceBoList);
        return userCityBo;
    }

    @Override
    public void saveUserCities(String unionUid, List<String> cities, String createdBy) {
        if (unionUid == null) {
            throw new IllegalArgumentException("unionUid不允许为空");
        }
        Date now = new Date();
        UserCityQuery userCityQuery = new UserCityQuery();
        userCityQuery.setCities(cities);
        userCityQuery.setUnionUid(unionUid);
        userCityMapper.deleteList(userCityQuery);
        for (String city : cities) {
            int count = userCityMapper.countUserCity(unionUid, city);
            if (count == 0) {
                UserCity userCity = new UserCity();
                userCity.setCityCode(city);
                userCity.setUnionUid(unionUid);
                userCity.setCreated(now);
                userCity.setCreatedby(createdBy);
                userCityMapper.insert(userCity);
            }
        }

    }
}
