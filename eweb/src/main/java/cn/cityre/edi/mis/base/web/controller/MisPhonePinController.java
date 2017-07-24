package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.mis.account.def.ActionType;
import cn.cityre.mis.account.entity.po.MisPhonePinPo;
import cn.cityre.mis.account.service.PhonePinService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.googlecode.genericdao.search.Search;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by cityre on 2017/7/12.
 */
@Controller(value = "misPhonePinController")
@RequestMapping(value = "/base/phonepin")
public class MisPhonePinController {
    private final static String URL="phone_pin";
    @Autowired
    private PhonePinService misPhonePinService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/phonepin/phonepin");
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS,PagingSettingResult.getDbPaging());
        return modelAndView;
    }
    @RequiresPermissions("view")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisPhonePinPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<MisPhonePinPo> paginationResult = misPhonePinService.getMisPhonePinList(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisPhonePinPo> get(Integer id){
        MisPhonePinPo misPhonePinPo = misPhonePinService.getPhonePinById(id);
        return JsonResult.success(misPhonePinPo);
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @RequiresPermissions("add")
    @ResponseBody
    public JsonResult<MisPhonePinPo> create(){
        MisPhonePinPo misPhonePinPo = new MisPhonePinPo();
        return JsonResult.success(misPhonePinPo);
    }
    @ResponseBody
    @RequiresPermissions("add")
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    public JsonResult<MisPhonePinPo> saveForCreated(HttpSession httpSession, @Validated @RequestBody MisPhonePinPo misPhonePinPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misPhonePinService.createPhonePin(misPhonePinPo);
        return JsonResult.success(misPhonePinPo);
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisPhonePinPo> saveForUpdated(HttpSession httpSession,@Validated @RequestBody MisPhonePinPo misPhonePinPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misPhonePinService.updatePhonePin(misPhonePinPo);
        return JsonResult.success(misPhonePinPo);
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisPhonePinPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misPhonePinService.deleteById(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/getActionTypeList",method = RequestMethod.GET)
    public JsonResult<String> getAcitonTypeList(){
        JsonObject list = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (ActionType actionType :ActionType.values()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key",actionType.getKey());
            jsonObject.addProperty("desc",actionType.getDesc());
            jsonArray.add(jsonObject);
        }
        list.add("actionTypeList",jsonArray);
        return JsonResult.success(list.toString());
    }
}
