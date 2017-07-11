package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.edi.mis.base.entity.po.ProductPo;
import cn.cityre.edi.mis.base.service.ProductService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
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
    public JsonResult<PaginationResult<ProductPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<ProductPo> paginationResult = productService.getProductList(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<ProductPo> get(HttpSession httpSession,String productCode){
        ProductPo productPo = productService.getExistProduct(productCode);
        return JsonResult.success(productPo);
    }
    @ResponseBody
    @RequiresPermissions(value = "add")
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public JsonResult<ProductPo> create(){
        ProductPo productPo = new ProductPo();
        return JsonResult.success(productPo);
    }
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    @RequiresPermissions("add")
    @ResponseBody
    public JsonResult<ProductPo> saveForCreated(HttpSession httpSession,@Validated @RequestBody ProductPo productPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);

        productService.saveProduct(productPo);
        return  JsonResult.success(productPo);
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<ProductPo> saveForUpdated(HttpSession httpSession,@Validated @RequestBody ProductPo productPo){
        productService.saveProduct(productPo);
        return JsonResult.success(productPo);
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<ProductPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<String> deleteParams){
        productService.deleteProducts(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
}
