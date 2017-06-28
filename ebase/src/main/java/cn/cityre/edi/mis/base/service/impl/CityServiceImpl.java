/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.service.impl;

import com.dsdl.eidea.core.spring.annotation.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cityre.edi.mis.base.entity.po.CityPo;
import cn.cityre.edi.mis.base.service.CityService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Search;
import com.dsdl.eidea.core.dao.CommonDao;
import java.util.List;
/**
 * @author 刘大磊 2017-06-28 15:47:46
 */
@Service("cityService")
public class CityServiceImpl  implements	CityService {
	@DataAccess(entity =CityPo.class)
	private CommonDao<CityPo,Integer> cityDao;
	public PaginationResult<CityPo> getCityListByPaging(Search search,QueryParams queryParams)
    {
		search.setFirstResult(queryParams.getFirstResult());
		search.setMaxResults(queryParams.getPageSize());
		PaginationResult<CityPo> paginationResult = null;
		if (queryParams.isInit()) {
		SearchResult<CityPo> searchResult = cityDao.searchAndCount(search);
		paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
		}
		else
		{
		List<CityPo> cityPoList = cityDao.search(search);
		paginationResult = PaginationResult.pagination(cityPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
		}
    	return paginationResult;
    }

    public CityPo getCity(Integer id)
	{
		return cityDao.find(id);
	}
    public void saveCity(CityPo city)
	{
		cityDao.save(city);
	}
    public void deletes(Integer[] ids)
	{
		cityDao.removeByIds(ids);
	}
}
