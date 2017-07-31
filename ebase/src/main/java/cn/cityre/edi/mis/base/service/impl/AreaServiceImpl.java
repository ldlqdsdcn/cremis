/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.service.impl;

import com.dsdl.eidea.core.spring.annotation.DataAccess;
import org.springframework.stereotype.Service;
import cn.cityre.edi.mis.base.entity.po.AreaPo;
import cn.cityre.edi.mis.base.service.AreaService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Search;
import com.dsdl.eidea.core.dao.CommonDao;
import java.util.List;
/**
 * @author 刘大磊 2017-06-28 15:50:20
 */
@Service("areaService")
public class AreaServiceImpl  implements	AreaService {
	@DataAccess(entity =AreaPo.class)
	private CommonDao<AreaPo,Integer> areaDao;
	public PaginationResult<AreaPo> getAreaListByPaging(Search search,QueryParams queryParams)
    {
		search.setFirstResult(queryParams.getFirstResult());
		search.setMaxResults(queryParams.getPageSize());
		PaginationResult<AreaPo> paginationResult = null;
		if (queryParams.isInit()) {
		SearchResult<AreaPo> searchResult = areaDao.searchAndCount(search);
		paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
		}
		else
		{
		List<AreaPo> areaPoList = areaDao.search(search);
		paginationResult = PaginationResult.pagination(areaPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
		}
    	return paginationResult;
    }

    public AreaPo getArea(Integer id)
	{
		return areaDao.find(id);
	}
    public void saveArea(AreaPo area)
	{
		areaDao.save(area);
	}
    public void deletes(Integer[] ids)
	{
		areaDao.removeByIds(ids);
	}
}
