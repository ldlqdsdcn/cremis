package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.edi.mis.base.def.PlatformType;
import cn.cityre.edi.mis.base.entity.po.MisProductPo;
import cn.cityre.edi.mis.base.service.ProductService;
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
import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Controller
@RequestMapping(value = "/base/product")
public class ProductController {
    private final static String URL="product";
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/product/product");
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PaginationResult<MisProductPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<MisProductPo> paginationResult = productService.getProductList(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisProductPo> get(HttpSession httpSession, String productCode){
        MisProductPo misProductPo = productService.getExistProduct(productCode);
        return JsonResult.success(misProductPo);
    }
    @ResponseBody
    @RequiresPermissions(value = "add")
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public JsonResult<MisProductPo> create(){
        Date date = new Date();
        MisProductPo misProductPo = new MisProductPo();
        misProductPo.setCreateTime(date);
        misProductPo.setUpdateTime(date);
        return JsonResult.success(misProductPo);
    }
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    @RequiresPermissions("add")
    @ResponseBody
    public JsonResult<MisProductPo> saveForCreated(HttpSession httpSession, @Validated @RequestBody MisProductPo misProductPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        productService.saveProduct(misProductPo);
        return  JsonResult.success(misProductPo);
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisProductPo> saveForUpdated(HttpSession httpSession, @Validated @RequestBody MisProductPo misProductPo){
        Date date = new Date();
        misProductPo.setUpdateTime(date);
        productService.saveProduct(misProductPo);
        return JsonResult.success(misProductPo);
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisProductPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<String> deleteParams){
        productService.deleteProducts(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/getExistProductList",method = RequestMethod.POST)
    public JsonResult<Boolean> getExistProductList(@RequestBody MisProductPo misProductPo){
        boolean flag = true;
        if (productService.findExistProductByProductCode(misProductPo.getProductCode())){
            return JsonResult.success(flag);
        }else {
            return JsonResult.success(!flag);
        }
    }
    @ResponseBody
    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/getPlatformTypeList",method = RequestMethod.GET)
    public JsonResult<String> getPlatformTypeList(){
        JsonArray jsonArray= new JsonArray();
        JsonObject jsonObject = new JsonObject();
        for (PlatformType platformType : PlatformType.values()){
            JsonObject list = new JsonObject();
            list.addProperty("key",platformType.getKey());
            list.addProperty("value",platformType.getValue());
            jsonArray.add(list);
        }
        jsonObject.add("platformTypeList",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
}
