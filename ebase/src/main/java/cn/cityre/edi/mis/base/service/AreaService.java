/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.service;
import cn.cityre.edi.mis.base.entity.po.AreaPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import java.util.List;

/**
 * @author 刘大磊 2017-06-28 15:50:20
 */
public interface AreaService {
	PaginationResult<AreaPo> getAreaListByPaging(Search search,QueryParams queryParams);
	AreaPo getArea(Integer id);
	void saveArea(AreaPo area);
	void deletes(Integer[] ids);
}