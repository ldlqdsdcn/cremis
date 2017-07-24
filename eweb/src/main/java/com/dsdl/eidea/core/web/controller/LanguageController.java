package com.dsdl.eidea.core.web.controller;

import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.entity.bo.LanguageBo;
import com.dsdl.eidea.core.entity.bo.LanguageTrlBo;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.service.LanguageService;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.dsdl.eidea.util.StringUtil;
import com.googlecode.genericdao.search.Search;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/12/8 17:37.
 */
@Controller
@RequestMapping("/core/language")
public class LanguageController {
    private static final String URI = "core_language";
    @Autowired
    private LanguageService languageService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/core/language/language");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI, URI);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<PaginationResult<LanguageBo>> list(HttpSession session, @RequestBody QueryParams queryParams) {
        Search search = SearchHelper.getSearchParam(URI, session);
        PaginationResult<LanguageBo> languageBoList = languageService.findLanguage(search,queryParams);
        return JsonResult.success(languageBoList);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<LanguageBo> get(String code) {
        LanguageBo languageBo = null;
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (StringUtil.isEmpty(code)) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("client.msg.primary_key_is_empty"));
        } else {
            languageBo = languageService.getLanguageBo(code);
        }
        return JsonResult.success(languageBo);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<LanguageBo> create() {
        LanguageBo languageBo = new LanguageBo();
        languageBo.setCreated(true);
        languageBo.setIsactive("N");
        List<LanguageTrlBo> languageTrlBoList = new ArrayList<>();
        List<LanguageBo> languageBoList = languageService.findLanguageListForActivated();
        languageBoList.forEach(e -> {
            LanguageTrlBo languageTrlBo = new LanguageTrlBo();
            languageTrlBo.setLang(e.getCode());
            languageTrlBoList.add(languageTrlBo);
        });
        languageBo.setLanguageTrlBoList(languageTrlBoList);
        return JsonResult.success(languageBo);
    }

    /**
     * @param languageBo
     * @return
     */
    @RequestMapping(value = "/saveForUpdated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<LanguageBo> saveForUpdated(@RequestBody LanguageBo languageBo) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (languageBo.getCode() == null || languageBo.getCode().isEmpty()) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("common.primary_key.isempty"));
        }
        if (languageService.findExistLanguageName(languageBo.getName())) {
            if (languageService.findExistLanguageByName(languageBo.getName()).getCode().equals(languageBo.getCode())) {
                languageService.save(languageBo);
            } else {
                return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("language.error.name.exist"));
            }
        } else {
            languageService.save(languageBo);
        }
        return get(languageBo.getCode());
    }

    @RequestMapping(value = "/saveForCreated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<LanguageBo> saveForCreated(@RequestBody LanguageBo languageBo) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (languageService.findExistLanguage(languageBo.getCode())) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("language.msg.code_exists"));
        }
        if (languageService.findExistLanguageName(languageBo.getName())) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("language.error.name.exist"));
        }
        languageService.save(languageBo);
        return get(languageBo.getCode());
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<LanguageBo>> deletes(@RequestBody DeleteParams<String> deleteParams, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (deleteParams.getIds() == null || deleteParams.getIds().length == 0) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("client.msg.select_delete"));
        }
        languageService.deletes(deleteParams.getIds());
        return list(session,deleteParams.getQueryParams());
    }
    @RequestMapping(value = "/getLanguageList",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<List<LanguageBo>> getLanguageList(){
        List<LanguageBo> languageBoList = languageService.getLanguageForActivated();
        return JsonResult.success(languageBoList);
    }
}



