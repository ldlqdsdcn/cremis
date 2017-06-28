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
import cn.cityre.edi.mis.base.entity.po.ProvincePo;
import cn.cityre.edi.mis.base.service.ProvinceService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Search;
import com.dsdl.eidea.core.dao.CommonDao;
import java.util.List;
/**
 * @author 刘大磊 2017-06-28 15:46:13
 */
@Service("provinceService")
public class ProvinceServiceImpl  implements	ProvinceService {
	@DataAccess(entity =ProvincePo.class)
	private CommonDao<ProvincePo,Integer> provinceDao;
	public PaginationResult<ProvincePo> getProvinceListByPaging(Search search,QueryParams queryParams)
    {
		search.setFirstResult(queryParams.getFirstResult());
		search.setMaxResults(queryParams.getPageSize());
		PaginationResult<ProvincePo> paginationResult = null;
		if (queryParams.isInit()) {
		SearchResult<ProvincePo> searchResult = provinceDao.searchAndCount(search);
		paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
		}
		else
		{
		List<ProvincePo> provincePoList = provinceDao.search(search);
		paginationResult = PaginationResult.pagination(provincePoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
		}
    	return paginationResult;
    }

    public ProvincePo getProvince(Integer id)
	{
		return provinceDao.find(id);
	}
    public void saveProvince(ProvincePo province)
	{
		provinceDao.save(province);
	}
    public void deletes(Integer[] ids)
	{
		provinceDao.removeByIds(ids);
	}
}
