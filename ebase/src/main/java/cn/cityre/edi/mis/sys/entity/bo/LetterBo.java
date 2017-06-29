package cn.cityre.edi.mis.sys.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 9:57.
 */
@Getter
@Setter
public class LetterBo {
    private String firstLetter;
    private List<ProvinceAccessBo> provinceAccessBoList=new ArrayList<>();

    /**
     * 添加省份信息
     * @param provinceAccessBo
     */
    public void addProvinceAccessBo(ProvinceAccessBo provinceAccessBo)
    {
        provinceAccessBoList.add(provinceAccessBo);
    }
}
