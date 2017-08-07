package cn.cityre.edi.mis.mis.web.util;

import com.dsdl.eidea.base.service.SpringContextHolder;
import com.dsdl.eidea.common.web.vo.SearchColumnVo;
import com.dsdl.eidea.core.entity.dto.SearchColumnDto;
import com.dsdl.eidea.core.web.def.WebConst;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * MypaginationHelperSearch辅助类
 * Created by cityre on 2017/8/3.
 */
public class SearchFieldHelper {
    private static ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();

    public static List<SearchField> getSearchField(String uri, HttpSession httpSession) {
        List<SearchColumnVo> searchColumnVos = (List<SearchColumnVo>) httpSession.getAttribute(uri + WebConst.SESSION_SEARCH_PARAM);

        ModelMapper modelMapper = new ModelMapper();
        List<SearchColumnDto> searchColumnDtos = modelMapper.map(searchColumnVos, new TypeToken<List<SearchColumnDto>>() {
        }.getType());
        List<SearchField> searchFields = new ArrayList<SearchField>();
        if (searchColumnDtos==null){
            return searchFields;
        }
        for (SearchColumnDto searchColumnDto : searchColumnDtos) {
            SearchField searchField = new SearchField(searchColumnDto.getColumnName(), false, false, searchColumnDto.getValue());
            searchFields.add(searchField);
        }
        return searchFields;
    }

}
