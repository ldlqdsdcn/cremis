package cn.cityre.mis.sys.web.controller;

import cn.cityre.mis.core.entity.result.PagingListResult;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.RepositoryService;
import cn.cityre.mis.util.DateTimeHelper;
import cn.cityre.mis.util.ExcelUtils;
import cn.cityre.mis.util.StringUtil;
import cn.cityre.mis.util.WebUtil;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

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
    @RequiresPermissions("repository:view")
    public String showList() {
        return "repository/repository";
    }

    @RequestMapping("/showForm")
    public String showForm() {
        return "repository/form.tpl";
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    @RequiresPermissions("repository:view")
    public PagingListResult<Repository> getRepositoryList(RepositoryQuery repositoryQuery, HttpServletRequest request) {
        log.info("========================================>");
        PagingListResult<Repository> pagingListResult = new PagingListResult<>();
        Page<Repository> pageMyBatis = (Page<Repository>) repositoryService.getRepositoryList(repositoryQuery);
        pagingListResult.setTotal(pageMyBatis.getTotal());
        pagingListResult.setRows(pageMyBatis);
        return pagingListResult;
    }

    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(RepositoryQuery repositoryQuery, HttpServletRequest request, HttpServletResponse response) {
        List<Repository> repositoryList = repositoryService.getRepositoryList(repositoryQuery);
        List<Map<String, Object>> data = new ArrayList<>();
        for (Repository repository : repositoryList) {
            Map<String, Object> row = new HashMap<>();
            row.put("no", repository.getNo());
            row.put("name", repository.getName());
            row.put("isactive", repository.getIsactive());

            row.put("created", DateTimeHelper.formatDateTime(repository.getCreated()));
            row.put("createdby", repository.getCreatedby());
            row.put("updated", DateTimeHelper.formatDateTime(repository.getUpdated()));
            row.put("updatedby", repository.getUpdatedby());
            data.add(row);
        }

        try {
            ExcelUtils.export("授权点", "授权点", new String[]{"编号", "名称", "是否有效", "创建时间", "创建人", "修改时间", "修改人"}, new String[]{"no", "name", "isactive", "created", "createdby", "updated", "updatedby"},
                    null, null, data, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequiresPermissions("repository:edit")
    @RequestMapping(value = "/deleteRepositories", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Void> deleteRepositories(String idsString, HttpServletRequest request) {
        Integer[] ids = StringUtil.convertStringToIntegerArray(idsString);
        if (ids == null || ids.length == 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "请先选中再删除");
        }
        repositoryService.removeRepositoryList(ids);

        return JsonResult.success(null);
    }

    @RequiresPermissions("repository:edit")
    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Repository get(@PathVariable("id") Integer id) {
        return repositoryService.getRepository(id);
    }

    @RequiresPermissions("repository:edit")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Repository> save(@Valid Repository repository, BindingResult bindingResult, HttpServletRequest request) {
        JsonResult error = WebUtil.hasErrorMessage(bindingResult);
        if (error != null) {
            return error;
        }
        String username = WebUtil.getUserSession(request).getUnionUid();
        if (StringUtil.isEmpty(repository.getNo())) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "编号不允许为空");
        }
        if (StringUtil.isEmpty(repository.getName())) {
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
