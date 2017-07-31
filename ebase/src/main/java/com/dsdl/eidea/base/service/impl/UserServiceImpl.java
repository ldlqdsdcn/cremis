package com.dsdl.eidea.base.service.impl;

import cn.cityre.edi.mis.base.dao.CityDao;
import cn.cityre.edi.mis.base.dao.ProvinceDao;
import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.edi.mis.base.entity.cpo.ProvincePo;
import cn.cityre.edi.mis.sys.entity.bo.CityCanAccessedBo;
import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import cn.cityre.edi.mis.sys.entity.bo.ProvinceAccessBo;
import cn.cityre.edi.mis.sys.entity.po.UserCityAccessPo;
import com.dsdl.eidea.base.service.AccountService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.dsdl.eidea.base.def.OperatorDef;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.base.entity.bo.UserContent;
import com.dsdl.eidea.base.entity.bo.UserRoleBo;
import com.dsdl.eidea.base.entity.bo.UserSessionBo;
import com.dsdl.eidea.base.entity.po.*;
import com.dsdl.eidea.base.service.UserService;
import com.dsdl.eidea.base.util.JwtUtil;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.util.ChineseCharToEn;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Bobo on 2016/12/17 14:02.
 */
@Service
public class UserServiceImpl implements UserService {
    @DataAccess(entity = UserPo.class)
    private CommonDao<UserPo, Integer> userDao;
    @DataAccess(entity = RolePo.class)
    private CommonDao<RolePo, Integer> roleDao;
    @DataAccess(entity = UserRolePo.class)
    private CommonDao<UserRolePo, Integer> userRoleDao;
    @DataAccess(entity = UserSessionPo.class)
    private CommonDao<UserSessionPo, Integer> userSessionDao;
    @DataAccess(entity = UserCityAccessPo.class)
    private CommonDao<UserCityAccessPo, Integer> userCityAccessDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private AccountService accountService;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PaginationResult<UserBo> getUserList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<UserBo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<UserPo> searchResult = userDao.searchAndCount(search);
            List<UserBo> list = modelMapper.map(searchResult.getResult(), new TypeToken<List<UserBo>>() {
            }.getType());
            paginationResult = PaginationResult.pagination(list, searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<UserPo> userPoList = userDao.search(search);
            List<UserBo> userBoList = modelMapper.map(userPoList, new TypeToken<List<UserBo>>() {
            }.getType());
            paginationResult = PaginationResult.pagination(userBoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public void deleteUserList(Integer[] ids) {
        for (Integer id : ids) {
            accountService.deleteUser(id);
        }
        userDao.removeByIdsForLog(ids);

    }

    @Override
    public void saveUser(UserBo userBo) {
        if (userBo.getIsactive() == null) {
            userBo.setIsactive("Y");
        } else if (userBo.getInit() == null) {
            userBo.setInit("N");
        }
        UserPo userPo = modelMapper.map(userBo, UserPo.class);
        if (userBo.getId() != null && userBo.getId() > 0) {
            Search search = new Search();
            search.addFilterIn("sysUser.id", userBo.getId());
            List<UserRolePo> userRolePoList = userRoleDao.search(search);
            if (userRolePoList != null && userRolePoList.size() > 0) {
                for (UserRolePo userRolePo : userRolePoList) {
                    userRoleDao.removeForLog(userRolePo);
                }
            }
        }
        if (userBo.getRoleIds() != null && userBo.getRoleIds().length > 0) {
            List<UserRolePo> sysUserRoles = new ArrayList<>();
            Integer[] roleIds = userBo.getRoleIds();
            for (Integer id : roleIds) {
                UserRolePo userRolePo = new UserRolePo();
                RolePo roleP = roleDao.find(id);
                userRolePo.setSysRole(roleP);
                userRolePo.setSysUser(userPo);
                sysUserRoles.add(userRolePo);
            }
            userPo.setSysUserRoles(sysUserRoles);
        }
        if (userPo.getPassword() != null && userPo.getPassword().length() != 32) {
            userPo.setPassword(userPo.getPassword());
        }
        userDao.saveForLog(userPo);
        userBo.setId(userPo.getId());
        accountService.saveUser(userPo.getId());
    }

    @Override
    public UserBo getUser(Integer id) {
        UserPo userPo = userDao.find(id);
        UserBo userBo = modelMapper.map(userPo, UserBo.class);
        if (userPo != null) {
            List<UserRoleBo> userRoleBoList = modelMapper.map(userPo.getSysUserRoles(), new TypeToken<List<UserRoleBo>>() {
            }.getType());
            if (userRoleBoList != null && userRoleBoList.size() > 0) {
                Integer[] ids = new Integer[userRoleBoList.size()];
                for (int i = 0; i < userRoleBoList.size(); i++) {
                    ids[i] = userRoleBoList.get(i).getSysRoleId();
                }
                userBo.setRoleIds(ids);
            }
        }
        return userBo;
    }

    @Override
    public UserBo getUserLogin(String username, String password) {
        UserBo userBoOne = null;
        try {
            Search search = new Search();
            search.addFilterEqual("username", username);
            search.addFilterEqual("password", password);
            UserPo userLogin = userDao.searchUnique(search);
            userBoOne = modelMapper.map(userLogin, new TypeToken<UserBo>() {
            }.getType());

        } catch (Exception e) {
            return null;
        }
        return userBoOne;

    }

    @Override
    public boolean findExistByUsername(String username) {
        Search search = new Search();
        search.addFilterEqual("username", username);
        int count = userDao.count(search);
        if (count != 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean getExistUserName(UserBo userBo) {
        Search search = new Search();
        search.addFilterIn("username", userBo.getUsername());
        List<UserPo> userPoList = userDao.search(search);
        if (userPoList != null && userPoList.size() > 0) {
            if (userPoList.get(0).getId().equals(userBo.getId())) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean containsModulePo(ModuleRolePo moduleRolePoParam, List<ModuleRolePo> distModuleRolePo) {
        for (ModuleRolePo moduleRolePo : distModuleRolePo) {
            if (moduleRolePo.getSysModule().getId().equals(moduleRolePoParam.getSysModule().getId())) {
                List<PrivilegesPo> distPrivilegeList = moduleRolePo.getSysPrivilegeses();
                List<PrivilegesPo> paramPrivilegeList = moduleRolePoParam.getSysPrivilegeses();
                for (PrivilegesPo privilegesPo : paramPrivilegeList) {
                    boolean has = false;
                    for (PrivilegesPo dist : distPrivilegeList) {
                        if (privilegesPo.getSysOperator().getId().equals(dist.getSysOperator().getId())) {
                            has = true;
                            break;
                        }
                    }
                    if (!has) {
                        distPrivilegeList.add(privilegesPo);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, List<OperatorDef>> getUserPrivileges(Integer userId) {

        UserPo userPo = userDao.find(userId);
        List<UserRolePo> userRolePoList = userPo.getSysUserRoles();
        List<ModuleRolePo> distModuleRolePo = new ArrayList<>();

        for (UserRolePo userRolePo : userRolePoList) {
            List<ModuleRolePo> moduleRolePoList = userRolePo.getSysRole().getSysModuleRoles();
            for (ModuleRolePo moduleRolePo : moduleRolePoList) {
                if (!containsModulePo(moduleRolePo, distModuleRolePo)) {
                    distModuleRolePo.add(moduleRolePo);
                }
            }

        }
        Map<String, List<OperatorDef>> privilegesMap = new HashMap<>();
        for (ModuleRolePo moduleRolePo : distModuleRolePo) {
            List<ModuleDirectoryPo> moduleDirectoryPoList = moduleRolePo.getSysModule().getSysModuleDirectories();
            for (ModuleDirectoryPo moduleDirectoryPo : moduleDirectoryPoList) {
                if (moduleDirectoryPo.getSysModule().getIsactive().equals("Y")) {
                    List<PrivilegesPo> privilegesPoList = moduleRolePo.getSysPrivilegeses();
                    List<OperatorDef> operatorDefs = new ArrayList<>();
                    for (PrivilegesPo privilegesPo : privilegesPoList) {
                        operatorDefs.add(OperatorDef.getOperatorDefByKey(privilegesPo.getSysOperator().getNo()));
                    }
                    if (!privilegesMap.containsKey(moduleDirectoryPo.getSysDirectory().getDirectory())) {
                        privilegesMap.put(moduleDirectoryPo.getSysDirectory().getDirectory(), operatorDefs);
                    } else {
                        List<OperatorDef> distOperatorList = privilegesMap.get(moduleDirectoryPo.getSysDirectory().getDirectory());
                        mergeOperator(distOperatorList, operatorDefs);
                        privilegesMap.put(moduleDirectoryPo.getSysDirectory().getDirectory(), distOperatorList);
                    }

                }

            }

        }
        return privilegesMap;
    }

    private void mergeOperator(List<OperatorDef> distOperatorList, List<OperatorDef> operatorDefs) {
        for (OperatorDef operatorDef : operatorDefs) {
            boolean has = false;
            for (OperatorDef o2 : distOperatorList) {
                if (o2 == operatorDef) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                distOperatorList.add(operatorDef);
            }
        }
    }

    @Override
    public String generateToken(UserBo userBo) {
        return JwtUtil.getTokenString(userBo);
    }

    public UserSessionBo saveUserSessionBo(UserSessionBo userSessionBo) {
        UserSessionPo userSessionPo = modelMapper.map(userSessionBo, UserSessionPo.class);
        userSessionPo.setUserPo(userDao.find(userSessionBo.getUserId()));
        userSessionDao.saveForLog(userSessionPo);
        userSessionBo.setId(userSessionPo.getId());
        return userSessionBo;
    }

    public List<Integer> getCanAccessOrgList(Integer userId) {
        List<Integer> orgIdList = new ArrayList<>();
        UserPo userPo = userDao.find(userId);
        List<UserRolePo> userRolePoList = userPo.getSysUserRoles();
        userRolePoList.forEach(e -> {
            e.getSysRole().getSysRoleOrgaccesses().forEach(d -> {
                orgIdList.add(d.getSysOrg().getId());
            });
        });
        return orgIdList;
    }

    @Cacheable(value = "userContentCache", key = "#token")
    public UserContent getUserContent(String token) {
        Search search = new Search();
        search.addFilterEqual("token", token);
        UserSessionPo userSessionPo = userSessionDao.searchUnique(search);
        UserSessionBo userSessionBo = modelMapper.map(userSessionPo, UserSessionBo.class);
        Map<String, List<OperatorDef>> privilegesMap = getUserPrivileges(userSessionBo.getUserId());
        List<Integer> orgIdList = getCanAccessOrgList(userSessionBo.getUserId());
        UserContent userContent = new UserContent(privilegesMap, userSessionBo, token, orgIdList);
        return userContent;
    }

    @Override
    public UserBo getUserByUsername(String username) {
        Search search = new Search();
        search.addFilterEqual("username", username);
        UserPo userPo = userDao.searchUnique(search);
        UserBo userBo = modelMapper.map(userPo, UserBo.class);
        if (userPo != null) {
            List<UserRoleBo> userRoleBoList = modelMapper.map(userPo.getSysUserRoles(), new TypeToken<List<UserRoleBo>>() {
            }.getType());
            if (userRoleBoList != null && userRoleBoList.size() > 0) {
                Integer[] ids = new Integer[userRoleBoList.size()];
                for (int i = 0; i < userRoleBoList.size(); i++) {
                    ids[i] = userRoleBoList.get(i).getSysRoleId();
                }
                userBo.setRoleIds(ids);
            }
        }
        return userBo;
    }

    @Override
    public void saveUserForProfile(UserBo userBo) {
        UserPo userPo = userDao.find(userBo.getId());
        userPo.setName(userBo.getName());
        userDao.saveForLog(userPo);
    }

    /**
     * @param userId
     * @return
     * @see UserService#getCanAccessOrgList(Integer)
     */
    @Override
    public List<LetterBo> getProvinceAccessList(Integer userId) {
        ChineseCharToEn chineseCharToEn = new ChineseCharToEn();
        Search search = new Search();
        search.addFilterEqual("userId", userId);
        List<UserCityAccessPo> userCityAccessPoList = userCityAccessDao.search(search);
        Search provinceSearch = new Search();
        //provinceSearch.addFilterEqual("isactive", "Y");
        List<ProvincePo> provincePoList = provinceDao.search(provinceSearch);
        Map<String, LetterBo> letterBoMap = new HashMap<>();
        List<ProvinceAccessBo> provinceAccessBoList = new ArrayList<>();
        for (ProvincePo provincePo : provincePoList) {
            String firstLetter = chineseCharToEn.getFirstLetter(provincePo.getProvince());
            LetterBo letterBo = letterBoMap.get(firstLetter);
            if (letterBo == null) {
                letterBo = new LetterBo();
                letterBo.setFirstLetter(firstLetter);
                letterBoMap.put(firstLetter, letterBo);
            }
            ProvinceAccessBo provinceAccessBo = new ProvinceAccessBo();
            provinceAccessBo.setName(provincePo.getProvince());
            provinceAccessBo.setProvinceNo(provincePo.getProvinceid());
            provinceAccessBo.setProvinceId(provincePo.getId());
            letterBo.addProvinceAccessBo(provinceAccessBo);
            provinceAccessBoList.add(provinceAccessBo);
        }
        Search citySearch = new Search();
        //citySearch.addFilterEqual("isactive", "Y");
        List<CityPo> cityPoList = cityDao.search(citySearch);

        cityPoList.forEach(cityPo -> {
            ProvinceAccessBo provinceAccessBo = getProvinceAccessBo(provinceAccessBoList, cityPo.getProvinceid());
            if (provinceAccessBo != null) {
                CityCanAccessedBo cityCanAccessedBo = new CityCanAccessedBo();
                cityCanAccessedBo.setSelected(isSelected(userCityAccessPoList, cityPo.getId()));
                cityCanAccessedBo.setCityId(cityPo.getId());
                cityCanAccessedBo.setCityName(cityPo.getCity());
                provinceAccessBo.addCityCanAccessedBo(cityCanAccessedBo);
            }

        });
        List<LetterBo> letterBoList = letterBoMap.values().stream().sorted((LetterBo letterBo1, LetterBo letterBo2) -> letterBo1.getFirstLetter().compareTo(letterBo2.getFirstLetter())).collect(Collectors.toList());
        return letterBoList;
    }

    @Override
    public void saveUserAccessCitiesPrivileges(Integer userId, List<LetterBo> letterBoList) {
        List<CityCanAccessedBo> cityCanAccessedBos = new ArrayList<>();
        letterBoList.forEach(e -> {
            e.getProvinceAccessBoList().forEach(p -> {
                List<CityCanAccessedBo> selectedList = p.getCityCanAccessedBoList().stream().filter(e2 -> e2.isSelected()).collect(Collectors.toList());
                cityCanAccessedBos.addAll(selectedList);
            });
        });
        Search search = new Search();
        search.addFilterEqual("userId", userId);
        search.addField("id");
        List<Integer> userCityAccessPoIdList = userCityAccessDao.search(search);
        for (Integer id : userCityAccessPoIdList) {
            userCityAccessDao.removeById(id);
        }
        for (CityCanAccessedBo cityCanAccessedBo : cityCanAccessedBos) {
            UserCityAccessPo userCityAccessPo = new UserCityAccessPo();
            userCityAccessPo.setCityId(cityCanAccessedBo.getCityId());
            userCityAccessPo.setUserId(userId);
            userCityAccessDao.save(userCityAccessPo);
        }
    }

    private boolean isSelected(List<UserCityAccessPo> userCityAccessPoList, Integer cityId) {
        return userCityAccessPoList.stream().anyMatch(e -> e.getCityId().equals(cityId));
    }

    private ProvinceAccessBo getProvinceAccessBo(List<ProvinceAccessBo> provinceAccessBoList, String provinceNo) {
        for (ProvinceAccessBo provinceAccessBo : provinceAccessBoList) {
            if (provinceAccessBo.getProvinceNo().equals(provinceNo)) {
                return provinceAccessBo;
            }
        }
        return null;
    }
}
