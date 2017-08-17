/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.edi.mis.base.entity.po.AreaPo;
import cn.cityre.edi.mis.base.service.AreaService;
import cn.cityre.mis.core.city.entity.po.CityAreaPo;
import cn.cityre.mis.core.city.service.CityAreaService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.controller.BaseController;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* Created by 刘大磊 on 2017-06-28 15:50:20.
*/ @Controller
@RequestMapping("/base/area")
@Scope("request")
public class AreaController extends BaseController {
private static final String URI = "area";
@Autowired
private CityAreaService cityAreaService;
@Autowired
private AreaService areaService;
@RequestMapping(value = "/showList", method = RequestMethod.GET)
@RequiresPermissions("view")
public ModelAndView showList() {
ModelAndView modelAndView = new ModelAndView("/base/area/area");
modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
modelAndView.addObject(WebConst.PAGE_URI, URI);
return modelAndView;
}
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("view")
public JsonResult<PaginationResult<CityAreaPo>> list(HttpSession session, @RequestBody QueryParams queryParams) {
        List<CityAreaPo> cityAreaPoList=cityAreaService.getAreaListByPaging();
        PaginationResult<CityAreaPo> paginationResult = null;
        paginationResult = PaginationResult.pagination(cityAreaPoList, cityAreaPoList.size(), queryParams.getPageNo(), queryParams.getPageSize());
        return JsonResult.success(paginationResult);
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<AreaPo> get(Integer id) {
        AreaPo areaPo = null;
        if (id == null) {
        return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),getMessage("common.errror.get_object",getLabel("area.title")));
        } else {
        areaPo = areaService.getArea(id);
        }
        return JsonResult.success(areaPo);
        }

        @RequiresPermissions("add")
        @RequestMapping(value = "/create", method = RequestMethod.GET)
        @ResponseBody
        public JsonResult<AreaPo> create() {
            AreaPo areaPo = new AreaPo();
            return JsonResult.success(areaPo);
            }

    /**
    * @param areaPo
    * @return
    */
    @RequiresPermissions("add")
    @RequestMapping(value = "/saveForCreated", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<AreaPo> saveForCreate(@Validated @RequestBody AreaPo areaPo) {
        areaService.saveArea(areaPo);
        return get(areaPo.getId());
        }

        @RequiresPermissions("update")
        @RequestMapping(value = "/saveForUpdated", method = RequestMethod.POST)
        @ResponseBody
        public JsonResult<AreaPo> saveForUpdate(@Validated @RequestBody AreaPo areaPo) {

            if(areaPo.getId() == null){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), getMessage("common.errror.pk.required"));
            }
            areaService.saveArea(areaPo);
            return get(areaPo.getId());
            }

    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody

    public JsonResult<PaginationResult<CityAreaPo>> deletes(@RequestBody DeleteParams<Integer> deleteParams, HttpSession session) {
    if (deleteParams.getIds() == null||deleteParams.getIds().length == 0)  {
                return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), getMessage("common.error.delete.failure",getMessage("area.title")));
                }
            areaService.deletes(deleteParams.getIds());
                return list(session,deleteParams.getQueryParams());
        }


}
