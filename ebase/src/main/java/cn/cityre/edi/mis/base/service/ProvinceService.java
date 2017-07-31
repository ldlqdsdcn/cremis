/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.edi.mis.base.entity.cpo.ProvincePo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import java.util.List;

/**
 * @author 刘大磊 2017-06-28 15:46:13
 */
public interface ProvinceService {
	PaginationResult<ProvincePo> getProvinceListByPaging(Search search,QueryParams queryParams);
	ProvincePo getProvince(Integer id);
	void saveProvince(ProvincePo province);
	void deletes(Integer[] ids);

	/**
	 * 获取用户可以操作的省份
	 * @param userId
	 * @return
	 */
	List<ProvincePo> getProvinceList(Integer userId);

	/**
	 * 获取用户可以操作的城市
	 * @param userId
	 * @param provinceId
	 * @return
	 */
	List<CityPo> getCityListByProvinceId(Integer userId,Integer provinceId);
}