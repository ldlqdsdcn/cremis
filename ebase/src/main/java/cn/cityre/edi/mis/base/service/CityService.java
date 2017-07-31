/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * @author 刘大磊 2017-06-28 15:47:46
 */
public interface CityService {
	PaginationResult<CityPo> getCityListByPaging(Search search,QueryParams queryParams);
	CityPo getCity(Integer id);
	void saveCity(CityPo city);
	void deletes(Integer[] ids);
}