package cn.cityre.mis.sys.web.controller;

import cn.cityre.mis.core.entity.result.PagingListResult;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.RepositoryService;
import cn.cityre.mis.util.StringUtil;
import org.mybatis.pagination.dto.PageMyBatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by 刘大磊 on 2017/8/28 12:26.
 * 授权点维护
 */
@RequestMapping("/sys/repository")
@Controller
public class RepositoryController {
    private Logger log = LoggerFactory.getLogger(RepositoryController.class);
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/showList")
    public String showList() {
        return "sys/repositories";
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public PagingListResult<Repository> getRepositoryList(RepositoryQuery repositoryQuery, HttpServletRequest request) {
        log.info("========================================>");
        PagingListResult<Repository> pagingListResult = new PagingListResult();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            System.out.println(name + "  =  " + request.getParameter(name));
        }
        PageMyBatis<Repository> pageMyBatis = (PageMyBatis<Repository>) repositoryService.getRepositoryList(repositoryQuery);
        System.out.println("total:" + pageMyBatis.getTotal());
        System.out.println("size:" + pageMyBatis.size());
        pagingListResult.setTotal(pageMyBatis.getTotal());
        pagingListResult.setRows(pageMyBatis);
        return pagingListResult;
    }

    @RequestMapping(value = "/deleteRepositories", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> deleteRepositories(@RequestBody Integer[] ids, HttpServletRequest request) {
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            System.out.println(name + "  =  " + request.getParameter(name));
        }
        if (ids == null || ids.length == 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "请先选中再删除");
        }
        repositoryService.removeRepositoryList(ids);

        return JsonResult.success(null);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Repository> save(@RequestBody Repository repository) {
        String username = "administrator";
        if(StringUtil.isEmpty(repository.getNo()))
        {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "编号不允许为空");
        }
        if(StringUtil.isEmpty(repository.getName()))
        {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "名称不允许为空");
        }
        Date date = new Date();
        repository.setUpdatedby(username);
        repository.setUpdated(date);
        if (repositoryService.existRepository(repository.getNo(), repository.getId())) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "编号为" + repository.getNo() + "的授权点已经存在");
        }

        repositoryService.saveRepository(repository);
        return JsonResult.success(repository);
    }


}
