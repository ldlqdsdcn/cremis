package cn.cityre.mis.core.dao.db;

import cn.cityre.edi.supporting.db.DataSourceContextHolder;

/**
 * Created by 刘大磊 on 2017/8/23 18:05.
 */
public class CityDataSourceUtil {
    /**
     * 切换数据源
     *
     * @param cityCode
     * @return
     */
    public static void changeDB(String cityCode) {

        DataSourceContextHolder.setDbType(cityCode);

    }
}
