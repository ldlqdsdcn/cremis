package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.edi.mis.base.def.SexType;
import cn.cityre.edi.mis.base.def.VerifiedType;
import cn.cityre.edi.mis.base.entity.po.MisUserPo;
import cn.cityre.edi.mis.base.service.UserService;
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
 * Created by cityre on 2017/7/11.
 */
@Controller(value = "v2017UserController")
@RequestMapping(value = "/base/v2017User")
public class UserController {
    private final static String URL="v2017User";
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/v2017User/user");
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<PaginationResult<MisUserPo>> list(HttpSession httpSession , @RequestBody QueryParams queryParams){
        PaginationResult<MisUserPo> paginationResult = userService.getExistUserList(SearchHelper.getSearchParam(URL,httpSession),queryParams);
        return JsonResult.success(paginationResult);
    }
    @RequiresPermissions("view")
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisUserPo> get(HttpSession httpSession, Integer id){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        MisUserPo misUserPo = userService.getUser(id);
        return JsonResult.success(misUserPo);
    }
    @RequiresPermissions("add")
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public JsonResult<MisUserPo> create(){
        MisUserPo misUserPo = new MisUserPo();
        return JsonResult.success(misUserPo);
    }
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("add")
    public JsonResult<MisUserPo> saveForCreated(HttpSession httpSession , @Validated @RequestBody MisUserPo misUserPo){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        userService.saveUser(misUserPo);
        return  JsonResult.success(misUserPo);
    }
    @RequiresPermissions("update")
    @ResponseBody
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisUserPo> saveForUpdated(HttpSession httpSession, @Validated@RequestBody MisUserPo misUserPo){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        userService.saveUser(misUserPo);
        return JsonResult.success(misUserPo);
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisUserPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer>deleteParams){
        userService.deleteUser(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
    @RequestMapping(value = "/getVerifiedType",method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<String> getVerifiedType(){
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        for (VerifiedType verifiedType : VerifiedType.values()){
            JsonObject jsonObjectList = new JsonObject();
            jsonObjectList.addProperty("key",verifiedType.getKey());
            jsonObjectList.addProperty("value",verifiedType.getDesc());
            jsonArray.add(jsonObjectList);
        }
        jsonObject.add("verifiedTypeList",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
    @RequestMapping(value = "/getSexType",method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<String> getSexType(){
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        for (SexType sexType : SexType.values()){
            JsonObject jsonObjectList = new JsonObject();
            jsonObjectList.addProperty("key",sexType.getKey());
            jsonObjectList.addProperty("desc",sexType.getDesc());
            jsonArray.add(jsonObjectList);
        }
        jsonObject.add("sexTypeList",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
}
